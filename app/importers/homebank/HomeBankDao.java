package importers.homebank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import models.Account;
import models.Category;
import models.HomeBank;
import models.Payee;
import models.Transaction;
import play.db.jpa.GenericModel.JPAQuery;
import play.db.jpa.JPA;

public class HomeBankDao {
	
	public void persist(HomeBank homeBank) {
		saveAccountsInDB(homeBank);
		saveCategoriesInDB(homeBank);
		savePayeesInDB(homeBank);
		saveTransactionsInDB(homeBank);
	}

	private static List<Transaction> saveTransactionsInDB(HomeBank homeBank) {
		List<Transaction> transactions = homeBank.getTransactions();
		
		for (Transaction transaction : transactions) {
    		if (notRegisteredInDb(transaction)) {
    			
    			if (transaction.account != null) {
	    			Account account = Account.find("byKey", transaction.account.key).first();
	    			transaction.account = account;
    			}

    			String sql = "select sum(t.amount) from transactions t where t.account=?";
    			BigDecimal balance = (BigDecimal) JPA.em().createQuery(sql).setParameter(1, transaction.account).getResultList().get(0);
    			
    			if (balance == null) {
    				balance = BigDecimal.ZERO;
    			}
    			
    			if (transaction.category != null) {
	    			Category category = Category.find("byKey", transaction.category.key).first();
	    			transaction.category = category;
    			}
    			
    			if (transaction.payee != null) {
	    			Payee payee = Payee.find("byKey", transaction.payee.key).first();
	    			transaction.payee = payee;
    			}
    			
    			transaction.user = homeBank.getUser();
    			transaction.save();
    		}
		}
		return transactions;
	}

	
	private static boolean notRegisteredInDb(Transaction transaction) {
		try {
			Date date = transaction.transactionDate;
			return Transaction.count("description = ? and amount = ? and transactionDate = ?", transaction.description, transaction.amount, date) == 0;
		}
		catch (Exception e) {
			return false;
		}
	}

	private static List<Payee> savePayeesInDB(HomeBank homeBank) {
		List<Payee> payees = homeBank.getPayees();
    	for (Payee payee : payees) {
			if (notFound(Payee.find("byName", payee.name))) {
				payee.user = homeBank.getUser();
				payee.save();
			}
		}
		return payees;
	}

	private static List<Account> saveAccountsInDB(HomeBank homeBank) {
		List<Account> accounts = homeBank.getAccounts();
		for (Account account : accounts) {
			if (notFound(Account.find("byName", account.name))) {
				account.user = homeBank.getUser();
				account.save();
			}
		}
		return accounts;
	}
    
    private static List<Category> saveCategoriesInDB(HomeBank homeBank) {
		List<Category> categories = homeBank.getCategories();
		for (Category category : categories) {
			if (notFound(Category.find("byName", category.name))) {
				category.user = homeBank.getUser();
				category.save();
			}
		}
		return categories;
	}
    
    private static boolean notFound(JPAQuery object) {
    	return object.fetch().size() == 0;
    }
}

package poupaniquel.importers.homebank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import models.Account;
import models.Category;
import models.HomeBank;
import models.Payee;
import models.Transaction;
import play.db.jpa.JPA;

public class HomeBankDao {
	
	public void persist(HomeBank homeBank) {
		saveAccountsInDB(homeBank.getAccounts());
		saveCategoriesInDB(homeBank.getCategories());
		savePayeesInDB(homeBank.getPayees());
		saveTransactionsInDB(homeBank.getTransactions());
	}

	private static List<Transaction> saveTransactionsInDB(List<Transaction> transactions) {
		
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

	private static List<Payee> savePayeesInDB(List<Payee> payees) {
    	for (Payee payee : payees) {
			if (!(Payee.find("byName", payee.name).fetch().size() > 0)) {
				payee.save();
			}
		}
		return payees;
	}

	private static List<Account> saveAccountsInDB(List<Account> accounts) {
		for (Account account : accounts) {
			if (!(Account.find("byName", account.name).fetch().size() > 0)) {
				account.save();
			}
		}
		return accounts;
	}
    
    private static List<Category> saveCategoriesInDB(List<Category> categories) {
		for (Category category : categories) {
			if (!(Category.find("byName", category.name).fetch().size() > 0)) {
				category.save();
			}
		}
		return categories;
	}
}

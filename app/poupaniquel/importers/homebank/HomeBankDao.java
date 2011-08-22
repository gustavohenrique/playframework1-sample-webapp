package poupaniquel.importers.homebank;

import java.util.List;

import models.Account;
import models.Category;
import models.HomeBank;
import models.Payee;
import models.Transaction;

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
    			Account account = Account.find("byKey", transaction.getAccount().getKey()).first();
    			transaction.setAccount(account);
    			
    			Category category = Category.find("byKey", transaction.getCategory().getKey()).first();
    			transaction.setCategory(category);
    			
    			Payee payee = Payee.find("byKey", transaction.getPayee().getKey()).first();
    			transaction.setPayee(payee);
    			
    			transaction.save();
    		}
		}
		return transactions;
	}

	
	private static boolean notRegisteredInDb(Transaction transaction) {
		try {
			return Transaction.count("description = ? and amount = ? and date = ?", transaction.getDescription(), transaction.getAmount(), transaction.getDate()) == 0;
		}
		catch (Exception e) {
			return false;
		}
	}

	private static List<Payee> savePayeesInDB(List<Payee> payees) {
    	for (Payee payee : payees) {
			if (!(Payee.find("byName", payee.getName()).fetch().size() > 0)) {
				payee.save();
			}
		}
		return payees;
	}

	private static List<Account> saveAccountsInDB(List<Account> accounts) {
		for (Account account : accounts) {
			if (!(Account.find("byName", account.getName()).fetch().size() > 0)) {
				account.save();
			}
		}
		return accounts;
	}
    
    private static List<Category> saveCategoriesInDB(List<Category> categories) {
		for (Category category : categories) {
			if (!(Category.find("byName", category.getName()).fetch().size() > 0)) {
				category.save();
			}
		}
		return categories;
	}
}

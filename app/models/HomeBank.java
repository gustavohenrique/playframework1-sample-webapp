package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeBank {

	private User user;
	
	private String version;
	
	private List<Account> accounts = new ArrayList();
	
	private List<Payee> payees = new ArrayList();
	
	private List<Category> categories = new ArrayList();
	
	private List<Transaction> transactions = new ArrayList();

	public List<Account> getAccounts() {
		Collections.sort (accounts, new Comparator() {  
            public int compare(Object o1, Object o2) {  
                Account a1 = (Account) o1;
                Account a2 = (Account) o2;
                return a1.key < a2.key ? -1 : (a1.key > a2.key ? +1 : 0);
            }  
        });
		return accounts;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}

	public List<Payee> getPayees() {
		Collections.sort (payees, new Comparator() {  
            public int compare(Object o1, Object o2) {  
                Payee a1 = (Payee) o1;
                Payee a2 = (Payee) o2;
                return a1.key < a2.key ? -1 : (a1.key > a2.key ? +1 : 0);
            }  
        });
		return payees;
	}

	public void addPayee(Payee payee) {
		payees.add(payee);
	}
	
	public List<Category> getCategories() {
		Collections.sort (categories, new Comparator() {  
            public int compare(Object o1, Object o2) {  
            	Category a1 = (Category) o1;
            	Category a2 = (Category) o2;
                return a1.key < a2.key ? -1 : (a1.key > a2.key ? +1 : 0);
            }  
        });
		return categories;
	}

	public void addCategory(Category category) {
		categories.add(category);
	}

	public List<Transaction> getTransactions() {
		Collections.sort (transactions, new Comparator() {  
            public int compare(Object o1, Object o2) {  
            	Transaction a1 = (Transaction) o1;
            	Transaction a2 = (Transaction) o2;
                return a1.transactionDate.before(a2.transactionDate) ? -1 : (a1.transactionDate.after(a2.transactionDate) ? +1 : 0);
            }  
        });
		
		return transactions;
	}
	
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
}

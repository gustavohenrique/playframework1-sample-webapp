package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Account;
import models.Category;
import models.Payee;
import models.Transaction;
import models.TransactionFilterOptions;
import play.mvc.Controller;

public class Transactions extends Controller {

	private static List<String> errors = new ArrayList<String>();
	
	public static void list() {
		render();
	}
	
	public static void filter(Long accountId) {
		Account account = Account.findById(accountId);
		if (account == null) {
			errors.add("No accounts founds by id " + accountId);
		}
		
		TransactionFilterOptions options = new TransactionFilterOptions();
		options.setAccount(account);
		options.setPagination(params.get("start"), params.get("limit"));
		options.setCategory(getCategoryIfHasIn(params.get("category")));
		options.setPayee(getPayeeIfHasIn(params.get("payee")));

    	List<Transaction> transactions = Transaction.filter(options);
    	if (transactions == null || transactions.size() == 0) {
    		errors.add("No transactions found for account " + account);
    	}

    	returnJson(transactions, Transaction.count());
    }
	
	private static Category getCategoryIfHasIn(String id) {
		return Category.findById(toLong(id));
	}
	
	private static Payee getPayeeIfHasIn(String id) {
		return Payee.findById(toLong(id));
	}

	public static void accounts() {
		List<Account> accounts = Account.find("order by name").fetch();
		returnJson(accounts, Account.count());
	}
	
	public static void categories() {
		List<Category> categories = Category.find("order by name").fetch();
		returnJson(categories, Category.count());
	}
	
	public static void payees() {
		List<Payee> payees = Payee.find("order by name").fetch();
		returnJson(payees, Payee.count());
	}
	
	
	private static void returnJson(Object object, Long size) {
		boolean success = true;
		
		if (errors.size() > 0) {
			success = false;
			object = errors;
		}
		
		Map result = new HashMap<String, Object>();
		result.put("success", success);
		result.put("data", object);
		result.put("total", size);
		
		renderJSON(result);
	}
	
	private static Long toLong(String value) {
		try {
			return Long.valueOf(value);
		}
		catch (Exception e) {
			return 0l;
		}
	}
}

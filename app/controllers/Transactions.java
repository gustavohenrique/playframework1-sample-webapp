package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Account;
import models.Transaction;
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

    	List<Transaction> transactions = Transaction.filterByAccount(account);
    	if (transactions == null || transactions.size() == 0) {
    		errors.add("No transactions found for account " + account);
    	}
    	
    	returnJson(transactions);
    }
	
	public static void accounts() {
		List<Account> accounts = Account.findAll();
		
		returnJson(accounts);
	}
	
	
	private static void returnJson(Object object) {
		boolean success = true;
		
		if (errors.size() > 0) {
			success = false;
			object = errors;
		}
		
		Map result = new HashMap<String, Object>();
		result.put("success", success);
		result.put("data", object);
		
		renderJSON(result);
	}
}

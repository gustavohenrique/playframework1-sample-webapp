package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Account;
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
		options.setPagination(params.get("startPage"), params.get("limitPage"));

    	List<Transaction> transactions = Transaction.filter(options);
    	if (transactions == null || transactions.size() == 0) {
    		errors.add("No transactions found for account " + account);
    	}
		
    	returnJson(transactions, transactions.size());
    }
	
	public static void accounts() {
		List<Account> accounts = Account.findAll();
		
		returnJson(accounts, accounts.size());
	}
	
	
	private static void returnJson(Object object, int size) {
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
}

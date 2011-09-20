package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Account;
import models.Transaction;
import play.mvc.Controller;

public class Transactions extends Controller {

	public static void list() {
		render();
	}
	
	public static void filter(Long accountId) {
		Account account = Account.findById(accountId);

    	List<Transaction> transactions = Transaction.filterByAccount(account);
    	
    	returnJson(transactions);
    }
	
	public static void accounts() {
		List<Account> accounts = Account.findAll();
		
		returnJson(accounts);
	}
	
	
	private static void returnJson(Object object) {
		boolean objectIsNotlNull = (object != null);
		
		Map result = new HashMap<String, Object>();
		result.put("success", String.valueOf(objectIsNotlNull));
		result.put("data", object);
		
		renderJSON(result);
	}
}

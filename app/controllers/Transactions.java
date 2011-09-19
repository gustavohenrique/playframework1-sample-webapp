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

    	List<Transaction> transactions = Transaction.filter(account);
    	
    	Map result = new HashMap<String, Object>();
    	result.put("success", "true");
		result.put("data", transactions);
    	
		renderJSON(result);
    }
	
	public static void accounts() {
		List<Account> accounts = Account.findAll();

		Map result = new HashMap<String, Object>();
		result.put("success", "true");
		result.put("data", accounts);
		
		renderJSON(result);
	}
}

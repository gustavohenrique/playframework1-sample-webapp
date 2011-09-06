package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Account;
import models.Transaction;
import play.mvc.Controller;
import poupaniquel.helpers.TransactionHelper;

public class Transactions extends Controller {

	public static void list() {
		Account account = Account.all().first();
    	List<Transaction> transactions = new TransactionHelper().getByAccount(account);
    	render(account, transactions);
    }
	
	public static void accounts() {
		Map map = new HashMap<String, Object>();
		List<Account> accounts = Account.findAll();
		map.put("success", "true");
		map.put("data", accounts);
		
		renderJSON(map);
	}
}

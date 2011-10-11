package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	private static String error;
	
	private static Long accountId = 0l;
	
	public static void list() {
		render();
	}
	
	public static void filter(Long accountId) {
		Transactions.accountId = accountId;
		
		Account account = Account.findById(accountId);
		if (account == null) {
			error = "No accounts founds by id " + accountId;
		}
		
		TransactionFilterOptions options = new TransactionFilterOptions();
		options.setAccount(account);
		options.setPagination(params.get("start"), params.get("limit"));
		options.setCategory(getCategoryIfHasIn(params.get("category")));
		options.setPayee(getPayeeIfHasIn(params.get("payee")));
		options.setStart(getDateIfHasIn(params.get("startDate")));
		options.setEnd(getDateIfHasIn(params.get("endDate")));

    	List<Transaction> transactions = Transaction.filter(options);
    	if (transactions == null || transactions.size() == 0) {
    		error = "No transactions found for account " + account.getName();
    	}

    	returnJson(transactions, Transaction.count());
    }
	
	private static Date getDateIfHasIn(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.parse(date);
		}
		catch (Exception e) { 
			return null;
		}
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
		
		if (error != null) {
			success = false;
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("accountId", Transactions.accountId);
			data.put("message", error);
			object = error;
		}
		
		Map result = new HashMap<String, Object>();
		result.put("success", success);
		result.put("data", object);
		result.put("total", size);
		
		renderJSON(result);
	}
	
	private static Category getCategoryIfHasIn(String id) {
		return Category.findById(toLong(id));
	}
	
	private static Payee getPayeeIfHasIn(String id) {
		return Payee.findById(toLong(id));
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

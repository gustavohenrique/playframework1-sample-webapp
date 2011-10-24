package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.Account;
import models.Category;
import models.Payee;
import models.Transaction;
import models.TransactionFilterOptions;
import play.data.binding.Binder;
import play.db.jpa.JPABase;
import play.mvc.Controller;
import utils.DateDeserializer;
import utils.GsonBinder;

public class Transactions extends Controller {

	private static String error;
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void list() {
		render();
	}
	
	public static void filter(Long accountId) {
		Account account = Account.findById(accountId);
		
		error = null;
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
    		error = "No transactions found for " + account.getName();
    	}

    	returnJson(transactions, Transaction.count());
    }
	
	public static void create(String accountId) {
		System.out.println(params); 
	}
	
	public static void delete(Long accountId, JsonObject body) {
		Transaction submited = null;
		try {
			error = null;
			Gson gson = new GsonBuilder()
			 .registerTypeAdapter(Date.class, new DateDeserializer())
		     .create();
	
			submited = gson.fromJson(body.get("data"), Transaction.class);
			Transaction transaction = Transaction.findById(submited.getId());
			transaction.delete();
			returnJson(transaction, 1);
		}
		catch (Exception e) {
			e.printStackTrace();
			returnJsonError(e.getMessage());
		}
		
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
		returnJson(accounts, accounts.size());
	}
	
	public static void categories() {
		List<Category> categories = Category.find("order by name").fetch();
		returnJson(categories, categories.size());
	}
	
	public static void payees() {
		List<Payee> payees = Payee.find("order by name").fetch();
		returnJson(payees, payees.size());
	}
	
	
	private static void returnJson(Object data, Integer size) {
		boolean success = (error == null);
		
		Map result = new HashMap<String, Object>();
		result.put("success", success);
		result.put("data", data);
		result.put("total", size);
		
		renderJSON(result);
	}
	
	private static void returnJsonError(String message) {
		Map result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("message", message);
		
		renderJSON(result);
	}
	
	private static void returnJson(Object data, Long size) {
		returnJson(data, size.intValue());
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

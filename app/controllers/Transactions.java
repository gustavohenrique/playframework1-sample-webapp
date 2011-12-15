package controllers;

import java.util.Date;
import java.util.List;

import models.Account;
import models.Category;
import models.Payee;
import models.Transaction;
import models.TransactionFilterOptions;
import play.data.binding.Binder;
import play.mvc.Controller;
import utils.ConverterUtil;
import utils.DateDeserializer;
import utils.ExtJSReturn;
import utils.GsonBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class Transactions extends JsonController {
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void index() {
		render();
	}
	
	public static void read(Long accountId) {
		try {
			Account account = Account.findById(accountId);
			TransactionFilterOptions options = new TransactionFilterOptions();
			options.setAccount(account);
			options.setPagination(params.get("start"), params.get("limit"));
			options.setCategory(getCategory(params.get("category")));
			options.setPayee(getPayee(params.get("payee")));
			options.setStart(ConverterUtil.toDate(params.get("startDate")));
			options.setEnd(ConverterUtil.toDate(params.get("endDate")));
	
	    	List<Transaction> transactions = Transaction.filter(options);
	    	if (transactions == null || transactions.size() == 0) {
	    		jsonError("No transactions found");
	    	}
	    	jsonOk(transactions, Transaction.count());
		}
		catch (Exception e) {
			jsonError(e.getMessage());
		}
    }
	
	public static void delete(Long accountId, JsonObject body) {
		Transaction submited = null;
		try {
			Gson gson = new GsonBuilder()
			 .registerTypeAdapter(Date.class, new DateDeserializer())
			 .serializeNulls()
		     .create();
	
			submited = gson.fromJson(body.get("data"), Transaction.class);
			Transaction transaction = Transaction.findById(submited.getId());
			transaction.delete();
			jsonOk(transaction, 1l);
		}
		catch (Exception e) {
			jsonError(e.getMessage());
		}
		
	}
	
	public static void create(String accountId) {
		System.out.println(params); 
	}
	
	public static void categories() {
		List<Category> categories = Category.find("order by name").fetch();
		jsonOk(categories, ConverterUtil.toLong(categories.size()));
	}
	
	public static void payees() {
		List<Payee> payees = Payee.find("order by name").fetch();
		jsonOk(payees, ConverterUtil.toLong((payees.size())));
	}
	
	private static Category getCategory(String id) {
		return Category.findById(ConverterUtil.toLong(id));
	}
	
	private static Payee getPayee(String id) {
		return Payee.findById(ConverterUtil.toLong(id));
	}
}

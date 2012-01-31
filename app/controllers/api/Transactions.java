package controllers.api;

import java.util.Date;
import java.util.List;

import models.Account;
import models.Transaction;
import play.data.binding.Binder;
import play.mvc.Controller;
import play.mvc.With;
import pojo.TransactionFilterOptions;
import utils.ConverterUtil;
import utils.DateDeserializer;
import utils.ExtJS;
import utils.GsonBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import controllers.Secure;

@With(Secure.class)
public class Transactions extends Controller {
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void read(Long id) {
		try {
			TransactionFilterOptions options = new TransactionFilterOptions();
			options.setUserId(Secure.user.id);
			options.setTransactionId(id);
			options.setAccountId(ConverterUtil.toLong(params.get("account")));
			options.setPagination(params.get("start"), params.get("limit"));
			options.setCategoryId(ConverterUtil.toLong(params.get("category")));
			options.setPayeeId(ConverterUtil.toLong(params.get("payee")));
			options.setStart(ConverterUtil.toDate(params.get("startDate")));
			options.setEnd(ConverterUtil.toDate(params.get("endDate")));
	
	    	List<Transaction> transactions = Transaction.filter(options);
	    	if (transactions == null || transactions.size() == 0) {
	    		ExtJS.error("No transactions found");
	    	}
	    	
	    	ExtJS.success(transactions, Transaction.count());
		}
		catch (Exception e) {
			ExtJS.error(e.getMessage());
		}
    }
	
	public static void delete(Long id) {
		try {
			Transaction transaction = Transaction.find("byUserAndId", Secure.user, id).first();
			transaction.delete();
			ExtJS.success(transaction, 1l);
		}
		catch (Exception e) {
			ExtJS.error(e.getMessage());
		}
	}
	
	public static void create(JsonObject body) {
        Transaction submited = getSubmitedTransaction(body);
        
        Transaction transaction = new Transaction();
        transaction.user = Secure.user;
        transaction.description = submited.description;
        transaction.amount = submited.amount;
        transaction.transactionDate = submited.transactionDate;
        transaction.account = submited.account;
        transaction.category = submited.category;
        transaction.payee = submited.payee;
        transaction.payment = submited.payment;
        
        validation.valid(transaction);
        if(validation.hasErrors()) {
            ExtJS.error("Validation error: "+validation.errors().get(0).toString());
        }
        
        try {
            transaction.save();
            ExtJS.success(transaction, 1l);
        }
        catch (Exception e) {
            ExtJS.error(e.getMessage());
        }
    }
	
	public static void update(JsonObject body) {
		try {
	        Transaction submited = getSubmitedTransaction(body);
	        
	        Transaction transaction = Transaction.find("byUserAndId", Secure.user, submited.id).first();
	        transaction.description = submited.description;
	        transaction.amount = submited.amount;
	        transaction.transactionDate = submited.transactionDate;
	        transaction.account = submited.account;
	        if (submited.category.id != null && submited.category.id > 0) {
	        	transaction.category = submited.category;
	        }
	        else {
	        	transaction.category = null;
	        }
	        transaction.payee = submited.payee;
	        transaction.payment = submited.payment;
	        
	        validation.valid(transaction);
	        if(validation.hasErrors()) {
	            ExtJS.error("Validation error: "+validation.errors().get(0).toString());
	        }
	        
	        transaction.save();
	        ExtJS.success(Transaction.findById(transaction.id), 1l);
		}
		catch (Exception e) {
			ExtJS.error(e.getMessage());
		}
		
    }
	
	private static Transaction getSubmitedTransaction(JsonObject body) {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateDeserializer())
            .serializeNulls()
            .create();

       return gson.fromJson(body.get("data"), Transaction.class);
    }
}

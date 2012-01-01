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


public class Transactions extends Users {
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void index() {
		render();
	}
	
	public static void read(Long id, Long accountId) {
		try {
			TransactionFilterOptions options = new TransactionFilterOptions();
			options.setUserId(user.id);
			options.setTransactionId(id);
			options.setAccountId(accountId);
			options.setPagination(params.get("start"), params.get("limit"));
			options.setCategoryId(ConverterUtil.toLong(params.get("category")));
			options.setPayeeId(ConverterUtil.toLong(params.get("payee")));
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
	
	public static void delete(Long id, Long accountId) {
		try {
			Account account = Account.findById(accountId);
			Transaction transaction = Transaction.find("byUserAndIdAndAccount", user, id, account).first();
			transaction.delete();
			jsonOk(transaction, 1l);
		}
		catch (Exception e) {
			jsonError(e.getMessage());
		}
	}
	
	
}

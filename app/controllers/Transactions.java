package controllers;

import java.util.Date;
import java.util.List;

import models.Account;
import models.Category;
import models.Payee;
import models.Transaction;
import models.User;
import play.modules.paginate.ValuePaginator;
import play.mvc.Controller;
import play.mvc.With;
import pojo.TransactionFilterOptions;
import utils.ConverterUtil;
import utils.DateDeserializer;
import utils.ExtJS;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@With(Secure.class)
public class Transactions extends Controller {
	
	public static void list() {
		try {
			User user = Users.getConnected();
			TransactionFilterOptions options = new TransactionFilterOptions();
			options.setUserId(user.id);
			
			options.setAccountId(ConverterUtil.toLong(params.get("account")));
			options.setPagination(params.get("start"), params.get("limit"));
			options.setCategoryId(ConverterUtil.toLong(params.get("category")));
			options.setPayeeId(ConverterUtil.toLong(params.get("payee")));
			options.setStart(ConverterUtil.toDate(params.get("startDate")));
			options.setEnd(ConverterUtil.toDate(params.get("endDate")));
	
	    	List<Transaction> transactions = Transaction.filter(options);
	    	ValuePaginator entities = new ValuePaginator(transactions);
	    	int total = transactions.size();

	    	List<Account> accounts = Account.find("byUserAndDisabled", user, false).fetch();
	    	List<Account> categories = Category.find("byUser", user).fetch();
	    	List<Account> payees = Payee.find("byUser", user).fetch();
	    	
	    	render(entities, total, accounts, categories, payees);
	    	
		}
		catch (Exception e) {
			render();
		}
	}
	
	public static void read(Long id, Long accountId) {
		try {
			TransactionFilterOptions options = new TransactionFilterOptions();
			options.setUserId(new User().id);
			options.setTransactionId(id);
			options.setAccountId(accountId);
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
	
	public static void delete(Long id, Long accountId) {
		try {
			Account account = Account.findById(accountId);
			Transaction transaction = Transaction.find("byUserAndIdAndAccount", new User(), id, account).first();
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
        transaction.user = new User();
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
        Transaction submited = getSubmitedTransaction(body);
        
        Transaction transaction = Transaction.find("byUserAndIdAndAccount", new User(), submited.id, submited.account).first();
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
        
        transaction.save();
        ExtJS.success(transaction, 1l);
    }
	
	private static Transaction getSubmitedTransaction(JsonObject body) {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateDeserializer())
            .serializeNulls()
            .create();

       return gson.fromJson(body.get("data"), Transaction.class);
    }
}

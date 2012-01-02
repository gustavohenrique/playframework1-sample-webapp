package controllers.api;

import java.util.List;

import models.Account;
import play.data.binding.Binder;
import utils.ConverterUtil;
import utils.ExtJS;
import utils.GsonBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Accounts extends Users {
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void read(Long id) {
		if (id != null) {
			Account account = Account.find("byUserAndIdAndDisabled", user, id, false).first();
			ExtJS.success(account, 1l);
		}
		else {
			List<Account> accounts = Account.find("byUserAndDisabled", user, false).fetch();
			ExtJS.success(accounts, ConverterUtil.toLong(accounts.size()));
		}
	}
	
	public static void delete(Long id) {
		try {
			Account account = Account.find("byUserAndId", user, id).first();
			account.disabled = true;
			account.save();
			ExtJS.success(account, 1l);
		}
		catch (Exception e) {
			ExtJS.error(e.getMessage());
		}
	}
	
	public static void create(JsonObject body) {
		Account submited = getSubmitedAccount(body);
		
		Account account = new Account();
		account.user = user;
		account.name = submited.name;
		account.number = submited.number;
		account.initial = submited.initial;
		
		validation.valid(account);
	    if(validation.hasErrors()) {
	    	ExtJS.error("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    try {
	    	account.save();
	    	ExtJS.success(account, 1l);
	    }
	    catch (Exception e) {
	    	ExtJS.error("");
		}
	}
	
	public static void update(JsonObject body) {
		Account submited = getSubmitedAccount(body);
		
		Account account = Account.findById(submited.id);
		account.name = submited.name;
		account.number = submited.number;
		account.initial = submited.initial;
		
	    validation.valid(account);
	    if(validation.hasErrors()) {
	    	ExtJS.error("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    account.save();
		ExtJS.success(account, 1l);
	}
	
	private static Account getSubmitedAccount(JsonObject body) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.fromJson(body.get("data"), Account.class);
	}
	
}

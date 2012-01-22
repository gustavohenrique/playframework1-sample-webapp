package controllers.crud;

import java.util.List;

import models.Account;
import play.data.binding.Binder;
import play.mvc.With;
import utils.ConverterUtil;
import utils.ExtJS;
import utils.GsonBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import controllers.Secure;
import controllers.Users;

@With(Secure.class)
public class Accounts extends Users {
	
	public static void list() {
		List<Account> models = Account.find("byUserAndDisabled", null, false).fetch();
		render(models);
	}
	
	public static void read(Long id) {
		if (id != null) {
			Account account = Account.find("byUserAndIdAndDisabled", null , id, false).first();
			render(account);
		}
	}
	
	public static void delete(Long id) {
		try {
			Account account = Account.find("byUserAndId", null, id).first();
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
		account.user = null;//Secure.user;
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
	
	public static void update(Long id, JsonObject body) {
		Account submited = getSubmitedAccount(body);
		
		Account account = Account.findById(id);
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

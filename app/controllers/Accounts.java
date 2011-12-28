package controllers;

import java.util.List;

import models.Account;
import play.data.binding.Binder;
import utils.ConverterUtil;
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
			Account account = Account.find("byUserAndId", user, id).first();
			jsonOk(account, 1l);
		}
		else {
			List<Account> accounts = Account.find("byUser", user).fetch();
			jsonOk(accounts, ConverterUtil.toLong(accounts.size()));
		}
	}
	
	public static void update(JsonObject body) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		Account submited = gson.fromJson(body.get("data"), Account.class);
		
		Account account = Account.findById(submited.id);
		account.name = submited.name;
		account.number = submited.number;
		account.initial = submited.initial;
		
	    validation.valid(account);
	    if(validation.hasErrors()) {
	    	jsonError("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    account.save();
		jsonOk(account, 1l);
	}
	
	
}

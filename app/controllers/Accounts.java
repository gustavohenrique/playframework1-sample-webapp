package controllers;

import java.util.List;

import models.Account;
import play.data.binding.Binder;
import play.mvc.Controller;
import utils.ConverterUtil;
import utils.ExtJSReturn;
import utils.GsonBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Accounts extends Controller {
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void read() {
		List<Account> accounts = Account.find("order by name").fetch();
		jsonOk(accounts, ConverterUtil.toLong(accounts.size()));
	}
	
	public static void update(Long accountId, JsonObject body) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		Account submited = gson.fromJson(body.get("data"), Account.class);
		
		Account account = Account.findById(submited.getId());
		account.setName(submited.getName());
		account.setNumber(submited.getNumber());
		account.setInitial(submited.getInitial());
		
	    validation.valid(account);
	    if(validation.hasErrors()) {
	    	jsonError("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    account.save();
		jsonOk(account, 1l);
	}
	
	private static void jsonError(String message) {
		renderJSON(ExtJSReturn.mapError(message));
	}
	
	private static void jsonOk(Object data, Long size) {
		if (size > 0) {
		    renderJSON(ExtJSReturn.mapOk(data, size));
		}
		renderJSON(ExtJSReturn.mapError("Object not found"));
	}
	
}

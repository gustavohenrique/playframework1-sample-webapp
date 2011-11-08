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

public class Accounts extends Controller {
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void read() {
		List<Account> accounts = Account.find("order by name").fetch();
		jsonOk(accounts, ConverterUtil.toLong(accounts.size()));
	}
	
	public static void update() {
		Account account = Account.findById(params.get("id"));
		account.edit("account", params.all());
	    validation.valid(account);
	    if(validation.hasErrors()) {
	    	account.refresh();
	    	jsonError("Validation error");
	    }
		jsonOk(account, 0l);
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

package controllers;

import java.util.List;

import models.Account;
import models.User;
import play.modules.paginate.ModelPaginator;
import play.mvc.Controller;
import play.mvc.With;
import utils.ExtJS;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import controllers.Secure;

@With(Secure.class)
public class Accounts extends Controller {
	
	public static void list(Long page) {
		ModelPaginator entities = new ModelPaginator(Account.class, "user=?", Users.getConnected()).orderBy("ID");
		int total = entities.size();
	    render(entities, total);

//		List<Account> entities = Account.find("byUserAndDisabled", Users.getConnected(), false).fetch();
//		render(entities);
	}
	
	public static void read(Long id) {
		if (id != null) {
			Account account = Account.find("byUserAndIdAndDisabled", Users.getConnected() , id, false).first();
			render(account);
		}
	}
	
	public static void delete(Long id) {
		try {
			Account account = Account.find("byUserAndId", Users.getConnected(), id).first();
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

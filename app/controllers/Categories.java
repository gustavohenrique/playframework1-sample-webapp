package controllers;

import java.util.List;

import models.Account;
import models.Category;
import models.User;
import play.data.binding.Binder;
import utils.ConverterUtil;
import utils.GsonBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Categories extends Users {
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void read(Long id) {
		if (id != null) {
			Category category = Category.find("byUserAndId", user, id).first();
			jsonOk(category, 1l);
		}
		else {
			List<Category> categories = Category.find("byUser", user).fetch();
			jsonOk(categories, ConverterUtil.toLong(categories.size()));
		}
	}
	
	public static void delete(Long id) {
		try {
			Category category = Category.find("byUserAndId", user, id).first();
			category.delete();
			jsonOk(category, 1l);
		}
		catch (Exception e) {
			jsonError(e.getMessage());
		}
	}
	
	public static void create(JsonObject body) {
		Category submited = getSubmitedCategory(body);
		
		Category category = new Category();
		category.user = user;
		category.name = submited.name;
		
		validation.valid(category);
	    if(validation.hasErrors()) {
	    	jsonError("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    try {
	    	category.save();
	    	jsonOk(category, 1l);
	    }
	    catch (Exception e) {
	    	jsonError("");
		}
	}
	
//	public static void update(JsonObject body) {
//		Account submited = getSubmitedAccount(body);
//		
//		Account account = Account.findById(submited.id);
//		account.name = submited.name;
//		account.number = submited.number;
//		account.initial = submited.initial;
//		
//	    validation.valid(account);
//	    if(validation.hasErrors()) {
//	    	jsonError("Validation error: "+validation.errors().get(0).toString());
//	    }
//	    
//	    account.save();
//		jsonOk(account, 1l);
//	}
	
	private static Category getSubmitedCategory(JsonObject body) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.fromJson(body.get("data"), Category.class);
	}
	
}

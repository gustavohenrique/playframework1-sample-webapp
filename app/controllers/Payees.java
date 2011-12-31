package controllers;

import java.util.List;

import models.Payee;
import models.Payee;
import models.User;
import play.data.binding.Binder;
import utils.ConverterUtil;
import utils.GsonBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Payees extends Users {
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void read(Long id) {
		if (id != null) {
			Payee payee = Payee.find("byUserAndId", user, id).first();
			jsonOk(payee, 1l);
		}
		else {
			List<Payee> payees = Payee.find("byUser", user).fetch();
			jsonOk(payees, ConverterUtil.toLong(payees.size()));
		}
	}
	
	public static void delete(Long id) {
		try {
			Payee payee = Payee.find("byUserAndId", user, id).first();
			payee.delete();
			jsonOk(payee, 1l);
		}
		catch (Exception e) {
			jsonError(e.getMessage());
		}
	}
	
	public static void create(JsonObject body) {
		Payee submited = getSubmitedPayee(body);
		
		Payee payee = new Payee();
		payee.user = user;
		payee.name = submited.name;
		
		validation.valid(payee);
	    if(validation.hasErrors()) {
	    	jsonError("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    try {
	    	payee.save();
	    	jsonOk(payee, 1l);
	    }
	    catch (Exception e) {
	    	jsonError("");
		}
	}
	
	public static void update(JsonObject body) {
		Payee submited = getSubmitedPayee(body);
		
		Payee payee = Payee.findById(submited.id);
		payee.name = submited.name;
		
	    validation.valid(payee);
	    if(validation.hasErrors()) {
	    	jsonError("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    payee.save();
		jsonOk(payee, 1l);
	}
	
	private static Payee getSubmitedPayee(JsonObject body) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.fromJson(body.get("data"), Payee.class);
	}
	
}

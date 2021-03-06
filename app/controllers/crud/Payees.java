package controllers.crud;

import java.util.List;

import models.Payee;
import models.User;
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
public class Payees extends Users {
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void read(Long id) {
		if (id != null) {
			Payee payee = Payee.find("byUserAndId", new User(), id).first();
			ExtJS.success(payee, 1l);
		}
		else {
			List<Payee> payees = Payee.find("byUser", new User()).fetch();
			ExtJS.success(payees, ConverterUtil.toLong(payees.size()));
		}
	}
	
	public static void delete(Long id) {
		try {
			Payee payee = Payee.find("byUserAndId", new User(), id).first();
			payee.delete();
			ExtJS.success(payee, 1l);
		}
		catch (Exception e) {
			ExtJS.error(e.getMessage());
		}
	}
	
	public static void create(JsonObject body) {
		Payee submited = getSubmitedPayee(body);
		
		Payee payee = new Payee();
		payee.user = new User();
		payee.name = submited.name;
		
		validation.valid(payee);
	    if(validation.hasErrors()) {
	    	ExtJS.error("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    try {
	    	payee.save();
	    	ExtJS.success(payee, 1l);
	    }
	    catch (Exception e) {
	    	ExtJS.error("");
		}
	}
	
	public static void update(JsonObject body) {
		Payee submited = getSubmitedPayee(body);
		
		Payee payee = Payee.findById(submited.id);
		payee.name = submited.name;
		
	    validation.valid(payee);
	    if(validation.hasErrors()) {
	    	ExtJS.error("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    payee.save();
		ExtJS.success(payee, 1l);
	}
	
	private static Payee getSubmitedPayee(JsonObject body) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.fromJson(body.get("data"), Payee.class);
	}
	
}

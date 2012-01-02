package controllers.api;

import models.User;
import play.data.binding.Binder;
import play.data.validation.Required;
import play.libs.Codec;
import play.mvc.Controller;
import utils.ExtJS;
import utils.GsonBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class Users extends Controller {
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	public static void create(JsonObject body) {
		User submited = getSubmitedUser(body);
		
		User user = new User();
    	user.username = submited.username;
    	user.password = Codec.hexSHA1(submited.password);
    	user.fullname = submited.fullname;
		
		validation.valid(user);
	    if(validation.hasErrors()) {
	    	ExtJS.error("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    try {
	    	user.save();
	    	ExtJS.success(user, 1l);
	    }
	    catch (Exception e) {
	    	ExtJS.error("");
		}
	}

	public static void authenticate() {
		if (validation.hasErrors()) {
            flash.keep("url");
            flash.error("secure.error");
            params.flash();
        }
        else {
        	String username = params.get("username");
    		String password = params.get("password");
    		
    		User user = getUserAccount(username, password);
	        if (user != null && user.id > 0) {
	        	session.put("token", createToken(username, String.valueOf(user.id)));
	        	ExtJS.success(user, 1l);
	        }
        }
		ExtJS.error("Login failed");
	}

	public static void authenticate(JsonObject body) {
		User submited = getSubmitedUser(body);
		
		validation.valid(submited);
	    if(validation.hasErrors()) {
	    	ExtJS.error("Validation error: "+validation.errors().get(0).toString());
	    }
	    
    	User user = getUserAccount(submited.username, submited.password);
        if (user != null && user.id > 0) {
        	session.put("token", createToken(submited.username, String.valueOf(user.id)));
        	ExtJS.success(user, 1l);
        }
    	ExtJS.error("Login failed");
	}
	
	private static String createToken(String username, String id) {
		return username + "-" + Codec.hexSHA1(username);
	}

	private static User getUserAccount(@Required String username, @Required String password) {
		try {
			String encrypted = Codec.hexSHA1(password);
			return User.find("byUsernameAndPassword", username, encrypted).first();
		}
		catch (Exception e) {
			return null;
		}
	}
	
	private static User getSubmitedUser(JsonObject body) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		User submited = gson.fromJson(body.get("data"), User.class);
		return submited;
	}
	
}

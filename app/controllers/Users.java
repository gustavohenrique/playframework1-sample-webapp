package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mysql.jdbc.Messages;

import models.Account;
import models.User;
import play.data.binding.Binder;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.libs.Codec;
import play.mvc.Before;
import utils.GsonBinder;

public class Users extends JsonController {
	
	protected static User user;
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	@Before(unless={"login", "authenticate", "logout", "signup", "create"})
    static void checkAccess() {
        if (! session.contains("token")) {
            flash.put("url", "GET".equals(request.method) ? request.url : "/");
            login();
        }
        
        user = getUserAccount(session.get("token"));
        if (! exists(user)) {
        	login();
        }
    }
	
	public static void login() {
		render();
	}
	
	public static void signup() {
		render();
	}
	
	public static void create(JsonObject body) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		User submited = gson.fromJson(body.get("data"), User.class);
		
		User user = new User();
    	user.username = submited.username;
    	user.password = Codec.hexSHA1(submited.password);
    	user.fullname = submited.fullname;
		
		validation.valid(user);
	    if(validation.hasErrors()) {
	    	jsonError("Validation error: "+validation.errors().get(0).toString());
	    }
	    
	    user.save();
		jsonOk(user, 1l);
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
	        	jsonOk(user, 1l);
	        }
        }
		jsonError("Login failed");
	}
	
	
	private static boolean exists(User user) {
		return user != null;
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
	
	private static User getUserAccount(String token) {
		try {
			String[] splited = token.split("-");
			String username = splited[0];
			String encrypted = splited[1];
			
			User user = User.find("byUsername", username).first();
			if (Codec.hexSHA1(user.username).equals(encrypted)) {
				return user;
			}
		}
		catch (Exception e) {}
		return null;
	}
	
}

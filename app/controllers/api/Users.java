package controllers.api;

import models.User;
import play.data.binding.Binder;
import play.data.validation.Required;
import play.libs.Codec;
import play.mvc.Before;
import play.mvc.Controller;
import utils.ExtJS;
import utils.GsonBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Users extends Controller {
	
	protected static User user;
	
	static {
        Binder.register(JsonObject.class, new GsonBinder());
    }
	
	@Before(unless={"authenticate", "logout", "create"})
    static void checkAccess() {
        if (! session.contains("token")) {
            flash.put("url", "GET".equals(request.method) ? request.url : "/");
            ExtJS.error("Access denied");
        }
        
        user = getUserAccount(session.get("token"));
        if (! exists(user)) {
            ExtJS.error("User not found");
        }
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
		
		validation.valid(user);
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
	
	private static User getSubmitedUser(JsonObject body) {
		Gson gson = new GsonBuilder().serializeNulls().create();
		User submited = gson.fromJson(body.get("data"), User.class);
		return submited;
	}
	
}

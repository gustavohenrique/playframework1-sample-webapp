package controllers;

import models.User;
import play.data.validation.Required;
import play.libs.Codec;
import play.mvc.Before;

public class Auth extends JsonController {
	
	@Before(unless={"login", "authenticate", "logout"})
    static void checkAccess() {
        if (! session.contains("token")) {
            flash.put("url", "GET".equals(request.method) ? request.url : "/");
            login();
        }
        
        if (! userExists(session.get("token"))) {
        	login();
        }
    }
	
	public static void login() {
		render();
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
	        }
        }
	}
	
	
	private static boolean userExists(String token) {
		try {
			String[] splited = token.split("-");
			String username = splited[0];
			String encrypted = splited[1];
			
			User user = User.find("byUsername", username).first();
			return Codec.hexSHA1(user.username).equals(encrypted);
		}
		catch (Exception e) {
			return false;
		}
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
	
}

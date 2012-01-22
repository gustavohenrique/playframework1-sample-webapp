package controllers;

import models.User;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.libs.Codec;


public class Users extends Secure.Security {
	
	public static void signup() {
		render();
	}
	
	public static void create(@Valid User user) {
		
		User newUser = new User();
    	newUser.username = user.username;
    	newUser.password = Codec.hexSHA1(user.password);
    	newUser.fullname = user.fullname;
		
		validation.valid(newUser);
	    if(validation.hasErrors()) {
	    	signup();
	    }
	    
	    try {
	    	newUser.save();
	    	redirect("/");
	    }
	    catch (Exception e) {
	    	signup();
		}
	}

	public static boolean authenticate(String username, String password) {
		User user = getUserAccount(username, password);
	    return (user != null && user.id > 0);
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

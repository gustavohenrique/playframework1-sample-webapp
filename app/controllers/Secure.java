package controllers;

import models.User;
import play.libs.Codec;
import play.mvc.Before;
import play.mvc.Controller;
import utils.ExtJS;

public class Secure extends Controller {
    
    public static User user;
    
    @Before(unless={"create", "authenticate", "login", "logout", "signup"})
    static void checkAccess() {
        if (! session.contains("token")) {
            flash.put("url", "GET".equals(request.method) ? request.url : "/");
            ExtJS.error("Access denied. Please go to login page!");
        }
        
        user = getUserAccount(session.get("token"));
        if (! exists(user)) {
            ExtJS.error("User not found. Please go to login page!");
        }
    }
    
    private static boolean exists(User user) {
        return user != null;
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

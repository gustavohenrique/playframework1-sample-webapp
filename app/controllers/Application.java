package controllers;

import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Application extends Controller {

    public static void login() {
        render();
    }
    
    public static void signup() {
        render();
    }
    
    public static void index() {
        render();
    }
}

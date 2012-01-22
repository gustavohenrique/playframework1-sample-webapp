package controllers.api;


import importers.homebank.HomeBankDao;
import importers.homebank.HomeBankImporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import models.HomeBank;
import models.User;
import play.mvc.With;
import utils.ExtJS;
import controllers.Secure;
import controllers.Users;

@With(Secure.class)
public class Importers extends Users {
	
    public static void upload(File file) {
    	validation.required(file);
    	
    	if (! validation.hasErrors()) {
	    	try {
				InputStream xmlFile = new FileInputStream(file);
				HomeBank homeBank = new HomeBankImporter().fromXml(xmlFile);
				homeBank.setUser(new User());
				
				new HomeBankDao().persist(homeBank);
				
				response.contentType = "text/html";
				ExtJS.success("File was successful imported!");
			}
			catch (Exception e) {
			    ExtJS.error(e.getMessage());
			}
    	}
    	
    	ExtJS.error("Validation error");
    }
}
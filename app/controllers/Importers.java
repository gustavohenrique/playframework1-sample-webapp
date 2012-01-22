package controllers;


import importers.homebank.HomeBankDao;
import importers.homebank.HomeBankImporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import models.HomeBank;
import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Importers extends Controller {
	
	public static void index() {
		render();
	}
	
    public static void upload(File file) {
    	validation.required(file);
    	
    	if (! validation.hasErrors()) {
	    	try {
				InputStream xmlFile = new FileInputStream(file);
				HomeBank homeBank = new HomeBankImporter().fromXml(xmlFile);
				homeBank.setUser(Users.getConnected());
				
				new HomeBankDao().persist(homeBank);
				
				flash.success("File upload successful");
			}
			catch (Exception e) {
			    flash.error(e.getMessage());
			}
    	}

    	index();
    }
}
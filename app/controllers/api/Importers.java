package controllers.api;


import importers.homebank.HomeBankDao;
import importers.homebank.HomeBankImporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import models.Account;
import models.HomeBank;
import utils.ExtJS;

public class Importers extends Users {
	
    public static void upload(File file) {
    	validation.required(file);
    	
    	if (! validation.hasErrors()) {
	    	try {
				InputStream xmlFile = new FileInputStream(file);
				HomeBank homeBank = new HomeBankImporter().fromXml(xmlFile);
				homeBank.setUser(user);
				
				new HomeBankDao().persist(homeBank);
				
				response.contentType = "text/html";
				List<Account> a = Account.findAll();
				ExtJS.success("File was successful imported!");
			}
			catch (Exception e) {
			    ExtJS.error(e.getMessage());
			}
    	}
    	
    	ExtJS.error("Validation error");
    }
}
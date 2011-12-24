package controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import play.data.validation.Error;
import play.mvc.Controller;
import poupaniquel.importers.homebank.HomeBankDao;
import poupaniquel.importers.homebank.HomeBankImporter;

public class Importers extends Users {

    public static void index() {
    	render();
    }
    
    public static void upload(File file) {
    	//validation.required(file);
    	validation.required(file).message("Select a file to import");
    	
    	if (! validation.hasErrors()) {
    	
	    	try {
				InputStream xmlFile = new FileInputStream(file);
				models.HomeBank homeBank = new HomeBankImporter(user).fromXml(xmlFile);
				
				new HomeBankDao(null).persist(homeBank);
				
				render(homeBank);
			}
			catch (Exception e) {
				validation.addError("", "Error reading the uploaded file. " + e.getMessage(), "");
			}
    	}
    	validation.keep();
    	
        index();
    }
    
    
}

package controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import models.HomeBank;
import poupaniquel.importers.homebank.HomeBankDao;
import poupaniquel.importers.homebank.HomeBankImporter;

public class Importers extends Users {
	
    public static void upload(File file) {
    	validation.required(file);
    	
    	if (! validation.hasErrors()) {
	    	try {
				InputStream xmlFile = new FileInputStream(file);
				HomeBank homeBank = new HomeBankImporter(user).fromXml(xmlFile);
				
				new HomeBankDao(null).persist(homeBank);
				
				response.contentType = "text/html";
				jsonOk("File was successful imported");
			}
			catch (Exception e) {
				jsonError(e.getMessage());
			}
    	}
    	jsonError("Validation error");
    }
}
package controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import models.Account;
import models.Category;
import models.Payee;
import models.Transaction;

import org.apache.commons.io.IOUtils;
import org.codehaus.groovy.tools.shell.commands.ShowCommand;

import play.Play;
import play.mvc.Controller;
import play.data.validation.Error;
import play.db.jpa.GenericModel.JPAQuery;
import play.db.jpa.JPABase;
import poupaniquel.homebank.HomeBankDao;
import poupaniquel.homebank.HomeBankImporter;

public class HomeBank extends Controller {

    public static void index() {
    	render();
    }
    
    public static void importer() {
    	List<JPABase> accounts = Account.findAll();
    	List<JPABase> categories = Category.findAll();
    	List<JPABase> payees = Payee.findAll();
    	List<JPABase> transactions = Transaction.findAll();
        render(accounts, categories, payees, transactions);
    }
    
    public static void upload(File file) {
    	//validation.required(file);
    	validation.required(file).message("Select a file to import");

    	try {
			InputStream xmlFile = new FileInputStream(file);
			models.HomeBank homeBank = new HomeBankImporter().fromXml(xmlFile);
			
			new HomeBankDao().persist(homeBank);
			
			//render(accounts, categories);
			index();
		}
		catch (Exception e) {}
    	
        render("HomeBank/index.html");
    }
    
    
}

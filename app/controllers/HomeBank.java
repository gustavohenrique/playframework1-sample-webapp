package controllers;

import importers.HomeBankImporter;

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

public class HomeBank extends Controller {

    public static void index() {
    	List<JPABase> accounts = Account.findAll();
    	List<JPABase> categories = Category.findAll();
    	List<JPABase> payees = Payee.findAll();
    	List<JPABase> transactions = Transaction.findAll();
        render(accounts, categories, payees, transactions);
    }

    public static void upload(File file) {
    	//validation.required(file);
    	Error error = validation.required(file).message("Select a file to import").error;

    	try {
			InputStream xmlFile = new FileInputStream(file);
			models.HomeBank homeBank = new HomeBankImporter().fromXml(xmlFile);
			
			List<Account> accounts = saveAccountsInDB(homeBank.getAccounts());
			List<Category> categories = saveCategoriesInDB(homeBank.getCategories());
			List<Payee> payees = savePayeesInDB(homeBank.getPayees());
			List<Transaction> transactions = saveTransactionsInDB(homeBank.getTransactions());
			
			//render(accounts, categories);
			index();
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        render("HomeBank/index.html");
    }
    
    private static List<Transaction> saveTransactionsInDB(List<Transaction> transactions) {
    	for (Transaction transaction : transactions) {
    		if (notRegisteredInDb(transaction)) {
    			Account account = Account.find("byKey", transaction.getAccount().getKey()).first();
    			transaction.setAccount(account);
    			
    			Category category = Category.find("byKey", transaction.getCategory().getKey()).first();
    			transaction.setCategory(category);
    			
    			Payee payee = Payee.find("byKey", transaction.getPayee().getKey()).first();
    			transaction.setPayee(payee);
    			
    			transaction.save();
    		}
		}
		return transactions;
	}

	private static boolean notRegisteredInDb(Transaction transaction) {
		return Transaction.count("description = ? and amount = ? and date = ?", transaction.getDescription(), transaction.getAmount(), transaction.getDate()) == 0;
	}

	private static List<Payee> savePayeesInDB(List<Payee> payees) {
    	for (Payee payee : payees) {
			if (!(Payee.find("byName", payee.getName()).fetch().size() > 0)) {
				payee.save();
			}
		}
		return payees;
	}

	private static List<Account> saveAccountsInDB(List<Account> accounts) {
		for (Account account : accounts) {
			if (!(Account.find("byName", account.getName()).fetch().size() > 0)) {
				account.save();
			}
		}
		return accounts;
	}
    
    private static List<Category> saveCategoriesInDB(List<Category> categories) {
		for (Category category : categories) {
			if (!(Category.find("byName", category.getName()).fetch().size() > 0)) {
				category.save();
			}
		}
		return categories;
	}
}

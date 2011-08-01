package controllers;

import importers.HomeBankImporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.codehaus.groovy.tools.shell.commands.ShowCommand;

import play.Play;
import play.mvc.Controller;
import play.data.validation.Error;

public class HomeBank extends Controller {

    public static void index() {
        render();
    }

    public static void upload(File file) {
    	//validation.required(file);
    	Error error = validation.required(file).message("Select a file to import").error;
    	
		try {
			InputStream xmlFile = new FileInputStream(file);
			
			models.HomeBank homeBank = new HomeBankImporter().fromXml(xmlFile);
			String version = homeBank.getVersion();
			render(version);
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
        render("HomeBank/index.html");
    }
}

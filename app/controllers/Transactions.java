package controllers;

import java.util.List;

import play.mvc.Controller;

import models.Transaction;

public class Transactions extends Controller {

	public static void list() {
    	List<Transaction> transactions = Transaction.findAll();
    	render(transactions);
    }
}

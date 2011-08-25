package controllers;

import java.util.List;

import models.Account;
import models.Transaction;
import play.mvc.Controller;
import poupaniquel.helpers.TransactionHelper;

public class Transactions extends Controller {

	public static void list() {
		Account account = Account.all().first();
    	List<Transaction> transactions = new TransactionHelper().getByAccount(account);
    	render(account, transactions);
    }
}

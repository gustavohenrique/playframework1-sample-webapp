package controllers;

import models.Account;
import models.Category;
import models.Payee;
import models.Transaction;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import play.mvc.Http.Response;

public class TransactionsTest extends FixturesAndLogin {

	private Account citibank;
	
	private Transaction transaction;
	
	public void setUp() {
		super.setUp();
		citibank = Account.find("byName", "Citibank").first();
		transaction = Transaction.find("byDescription", "Chocolate").first();
	}
	
	@Test
	public void testDontGetTransactionsIfDontHaveAccount() {
		Response response = GET("/transactions/read/account/0");
		
		JsonNode success = getNode("success", response);
	    assertEquals("false", success.toString());
	}
	
	@Test
	public void testGetAllTransactionsAccordingOfAccount() {
		Response response = GET("/transactions/read/account/" + citibank.id);
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
		
		JsonNode data = getNode("data", response);
		assertEquals(7, data.findValuesAsText("description").size());
		assertEquals(7, data.findValuesAsText("amount").size());
	}
	
	@Test
	public void testGetTransactionById() {
		Response response = GET("/transactions/read/" + transaction.id + "/account/" + citibank.id);
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
		JsonNode data = getNode("data", response);
		assertEquals(1, data.findValuesAsText("description").size());
		assertEquals(1, data.findValuesAsText("amount").size());
	}
	
	/*
	
	@Test
	public void testGetTransactionsPaginated() {
		Response response = GET("/transactions/read?accountId=" + citibank.getId() + "&start=1&limit=2");
		assertEquals(2, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsFilteredByCategory() {
		Category category = Category.find("byName", "Food").first();
		Response response = GET("/transactions/read?accountId=" + citibank.getId()+"&category=" + category.getId());
		assertEquals(4, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsFilteredByPayee() {
		Payee payee = Payee.find("byName", "Americanas").first();
		Response response = GET("/transactions/read?accountId=" + citibank.getId()+"&payee=" + payee.getId());
		assertEquals(1, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsFilteredByStartDateUntilNow() {
		Response response = GET("/transactions/read?accountId=" + citibank.getId()+"&startDate=2011-08-06");
		assertEquals(1, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsFilteredByEndDate() {
		Response response = GET("/transactions/read?accountId=" + citibank.getId()+"&endDate=2011-08-02");
		assertEquals(3, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsFilteredByDateInterval() {
		Response response = GET("/transactions/read?accountId=" + citibank.getId()+"&startDate=2011-08-02&endDate=2011-08-02");
		assertEquals(1, getTotalObjectsIn(response));
	}
	*/
}

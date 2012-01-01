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
	
	
	@Test
	public void testPaginateAllTransactions() {
		Response response = GET("/transactions/read/account/" + citibank.id + "?start=1&limit=2");
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
		JsonNode data = getNode("data", response);
		assertEquals(2, data.findValuesAsText("description").size());
		assertEquals(2, data.findValuesAsText("amount").size());
	}
	
	
	@Test
	public void testGetTransactionsFilteredByCategory() {
		Category category = Category.find("byName", "Food").first();
		Response response = GET("/transactions/read/account/" + citibank.id + "?category=" + category.id);
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
		JsonNode data = getNode("data", response);
		assertEquals(4, data.findValuesAsText("description").size());
		assertEquals(4, data.findValuesAsText("amount").size());
	}
	
	
	@Test
	public void testGetTransactionsFilteredByPayee() {
		Payee payee = Payee.find("byName", "Americanas").first();
		Response response = GET("/transactions/read/account/" + citibank.id + "?payee=" + payee.id);
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
		JsonNode data = getNode("data", response);
		assertEquals(1, data.findValuesAsText("description").size());
		assertEquals(1, data.findValuesAsText("amount").size());
	}
	
	@Test
	public void testGetTransactionsFilteredByStartDateUntilNow() {
		Response response = GET("/transactions/read/account/" + citibank.id + "?startDate=2011-08-06");
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
		JsonNode data = getNode("data", response);
		assertEquals(1, data.findValuesAsText("description").size());
		assertEquals(1, data.findValuesAsText("amount").size());
	}
	
	@Test
	public void testGetTransactionsFilteredByEndDate() {
		Response response = GET("/transactions/read/account/" + citibank.id + "?endDate=2011-08-02");
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
		JsonNode data = getNode("data", response);
		assertEquals(3, data.findValuesAsText("description").size());
		assertEquals(3, data.findValuesAsText("amount").size());
	}
	
	@Test
	public void testGetTransactionsFilteredByDateInterval() {
		Response response = GET("/transactions/read/account/" + citibank.id + "?startDate=2011-08-02&endDate=2011-08-02");
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
		JsonNode data = getNode("data", response);
		assertEquals(1, data.findValuesAsText("description").size());
		assertEquals(1, data.findValuesAsText("amount").size());
	}
	
	@Test
	public void testDeleteById() {
		Response response = DELETE("/transactions/delete/" + transaction.id + "/account/" + citibank.id);
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
		JsonNode data = getNode("data", response);
		assertEquals("Chocolate", data.findValuesAsText("description").get(0));
		assertEquals("-50.0", data.findValuesAsText("amount").get(0));
		assertEquals("admin@localhost.com", data.findValuesAsText("username").get(0));
		
		Transaction chocolate = Transaction.find("byDescription", "Chocolate").first();
		assertNull(chocolate);
	}
}

package controllers.api;

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

    private Payee payee;

    private Category category;
	
	public void setUp() {
		super.setUp();
		citibank = Account.find("byName", "Citibank").first();
		transaction = Transaction.find("byDescription", "Chocolate").first();
		payee = Payee.find("byName", "Cacau Show").first();
		category = Category.find("byName", "Food").first();
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
	
	@Test
    public void testCreateTransactionWithRequiredFields() {
        String body = "{data:{\"description\":\"my transaction\",\"amount\":\"100.0\",\"transactionDate\":\"2012-01-02\"," +
                        "\"account\":{\"id\":" + citibank.id + ",\"name\":\"" + citibank.name + "\",\"number\":\"" + citibank.number + "\",\"initial\":" + citibank.initial + "}}}";
        Response response = POST("/transactions/create", "application/json", body);
        
        JsonNode success = getNode("success", response);
        assertEquals("true", success.toString());
        
        Transaction persisted = Transaction.find("byDescription", "my transaction").first();
        assertNotNull(persisted);
    }
	
	
	@Test
    public void testCreateTransactionWithAllFields() {
        String body = "{data:{\"description\":\"my transaction\",\"amount\":\"100.0\",\"transactionDate\":\"2012-01-02\",\"payment\":1," +
                        "\"payee\":{\"id\":" + payee.id + ",\"name\":\"" + payee.name + "\"}," +
                        "\"category\":{\"id\":" + category.id + ",\"name\":\"" + category.name + "\"}," +
                        "\"account\":{\"id\":" + citibank.id + ",\"name\":\"" + citibank.name + "\",\"number\":\"" + citibank.number + "\",\"initial\":" + citibank.initial + "}}}";
        
        Response response = POST("/transactions/create", "application/json", body);
        
        JsonNode success = getNode("success", response);
        assertEquals("true", success.toString());
        
        Transaction persisted = Transaction.find("byDescription", "my transaction").first();
        assertNotNull(persisted);
    }
	
	@Test
    public void testFailCreateTransactionWithoutAccount() {
        String body = "{data:{\"description\":\"my transaction\",\"amount\":\"100.0\",\"transactionDate\":\"2012-01-02\"," +
                        "\"payee\":{\"id\":" + payee.id + ",\"name\":\"" + payee.name + "\"}," +
                        "\"category\":{\"id\":" + category.id + ",\"name\":\"" + category.name + "\"}}}";
        
        Response response = POST("/transactions/create", "application/json", body);
        
        JsonNode success = getNode("success", response);
        assertEquals("false", success.toString());
        
        Transaction persisted = Transaction.find("byDescription", "my transaction").first();
        assertNull(persisted);
    }
	
	@Test
    public void testUpdateTransaction() {
	    String body = "{data:{\"id\":" + transaction.id + ",\"description\":\"my transaction\",\"amount\":\"123.50\",\"transactionDate\":\"2012-01-02\"," +
                "\"payee\":{\"id\":" + payee.id + ",\"name\":\"" + payee.name + "\"}," +
                "\"category\":{\"id\":" + category.id + ",\"name\":\"" + category.name + "\"}," +
                "\"account\":{\"id\":" + citibank.id + ",\"name\":\"" + citibank.name + "\",\"number\":\"" + citibank.number + "\",\"initial\":" + citibank.initial + "}}}";

        Response response = POST("/transactions/update", "application/json", body);
        
        JsonNode success = getNode("success", response);
        assertEquals("true", success.toString());
        
        JsonNode data = getNode("data", response);
        assertEquals("123.5", data.findValuesAsText("amount").get(0));
        assertEquals("Jan 2, 2012 12:00:00 AM", data.findValuesAsText("transactionDate").get(0));
    }
}

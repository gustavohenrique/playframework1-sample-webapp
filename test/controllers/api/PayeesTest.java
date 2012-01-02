package controllers.api;

import models.Payee;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import play.db.jpa.JPA;
import play.db.jpa.JPABase;
import play.mvc.Http.Response;

public class PayeesTest extends FixturesAndLogin {
	
	private Payee wallmart;
	
	public void setUp() {
		super.setUp();
		wallmart = Payee.find("byName", "Wallmart").first();
	}
	
	@Test
	public void testGetAllCategoriesByUser() {
		Response response = GET("/payees/read");
		
		JsonNode data = getNode("data", response);
		assertEquals(9, data.findValuesAsText("user").size());
		assertEquals(9, data.findValuesAsText("key").size());
		assertEquals(9, data.findValuesAsText("name").size());
	}

	@Test
	public void testGetPayeeById() {
		Response response = GET("/payees/read/" + wallmart.id);
		
		JsonNode data = getNode("data", response);
		assertEquals("Wallmart", data.findValuesAsText("name").get(0));
		assertEquals("1", data.findValuesAsText("key").get(0));
		assertEquals("admin@localhost.com", data.findValuesAsText("username").get(0));
	}
	
	@Test
	public void testDeleteByIdAndRemoveDependenciesInTransactions() {
		Response response = DELETE("/payees/delete/" + wallmart.id);
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
		JsonNode data = getNode("data", response);
		assertEquals("Wallmart", data.findValuesAsText("name").get(0));
		assertEquals("1", data.findValuesAsText("key").get(0));
		assertEquals("admin@localhost.com", data.findValuesAsText("username").get(0));
		
		Payee category = Payee.find("byName", "Wallmart").first();
		assertNull(category);
	}
	
	@Test
	public void testCreatePayee() {
		String body = "{data:{\"name\":\"Other Payee\"}}";
	    Response response = POST("/payees/create", "application/json", body);
		
	    JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
	    JsonNode data = getNode("data", response);
	    assertEquals("Other Payee", data.findValuesAsText("name").get(0));
	}
	
	@Test
	public void testDontDuplicatePayee() {
		String body = "{data:{\"name\":\"Wallmart\"}}";
	    Response response = POST("/payees/create", "application/json", body);
		
	    JsonNode success = getNode("success", response);
	    assertEquals("false", success.toString());
	}
	
	@Test
	public void testUpdatePayeeName() {
		String body = "{data:{\"name\":\"Wal-Mart Stores\", \"id\":" + wallmart.id + "}}";
	    Response response = POST("/payees/update", "application/json", body);
		
	    JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
	    JsonNode data = getNode("data", response);
	    assertEquals("Wal-Mart Stores", data.findValuesAsText("name").get(0));
	}
}

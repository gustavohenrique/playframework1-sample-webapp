package controllers;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import play.mvc.Http.Response;

public class AccountsTest extends FixturesAndLogin {
	
	@Test
	public void testGetAllAccountsByUser() {
		Response response = GET("/accounts/read");
		JsonNode node = getNode("data", response);
		
		assertEquals("Citibank", node.findValuesAsText("name").get(0));
		assertEquals("25739904721", node.findValuesAsText("number").get(0));
		assertEquals("900.0", node.findValuesAsText("initial").get(0));
		
		assertEquals("Itau", node.findValuesAsText("name").get(1));
		assertEquals("6254", node.findValuesAsText("number").get(1));
		assertEquals("900.5", node.findValuesAsText("initial").get(1));
	}

	@Test
	public void testGetAccountById() {
		Long id = citibank.id;
		Response response = GET("/accounts/read/"+id);
		JsonNode node = getNode("data", response);
		
		assertEquals("Citibank", node.findValuesAsText("name").get(0));
		assertEquals("25739904721", node.findValuesAsText("number").get(0));
		assertEquals("900.0", node.findValuesAsText("initial").get(0));
	}
	
	@Test
	public void testUpdateAccountNumberAndInitial() {
		String body = "{data:{\"id\":" + citibank.id + ",\"name\":\"Citibank\",\"number\":\"17599\",\"initial\":85.90}}";
	    Response response = POST("/accounts/update", "application/json", body);
		
	    JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
	    JsonNode data = getNode("data", response);
	    assertEquals("Citibank", data.findValuesAsText("name").get(0));
		assertEquals("17599", data.findValuesAsText("number").get(0));
		assertEquals("85.9", data.findValuesAsText("initial").get(0));
	}
}

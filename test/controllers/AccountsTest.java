package controllers;

import models.Account;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import play.mvc.Http.Response;

public class AccountsTest extends FixturesAndLogin {
	
	private Account citibank;
	
	public void setUp() {
		super.setUp();
		citibank = Account.find("byName", "Citibank").first();
	}
	
	@Test
	public void testGetAllAccountsByUser() {
		Response response = GET("/accounts/read");
		
		JsonNode data = getNode("data", response);
		assertEquals("Citibank", data.findValuesAsText("name").get(0));
		assertEquals("25739904721", data.findValuesAsText("number").get(0));
		assertEquals("900.0", data.findValuesAsText("initial").get(0));
		assertEquals("false", data.findValuesAsText("disabled").get(0));
		
		assertEquals("Itau", data.findValuesAsText("name").get(1));
		assertEquals("6254", data.findValuesAsText("number").get(1));
		assertEquals("900.5", data.findValuesAsText("initial").get(1));
		assertEquals("false", data.findValuesAsText("disabled").get(1));
	}

	@Test
	public void testGetAccountById() {
		Response response = GET("/accounts/read/" + citibank.id);
		
		JsonNode data = getNode("data", response);
		assertEquals("Citibank", data.findValuesAsText("name").get(0));
		assertEquals("25739904721", data.findValuesAsText("number").get(0));
		assertEquals("900.0", data.findValuesAsText("initial").get(0));
		assertEquals("false", data.findValuesAsText("disabled").get(0));
	}
	
	@Test
	public void testDisableById() {
		Response response = DELETE("/accounts/delete/" + citibank.id);
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
		JsonNode data = getNode("data", response);
		assertEquals("Citibank", data.findValuesAsText("name").get(0));
		assertEquals("25739904721", data.findValuesAsText("number").get(0));
		assertEquals("900.0", data.findValuesAsText("initial").get(0));
		assertEquals("true", data.findValuesAsText("disabled").get(0));
	}
	
	@Test
	public void testDontGetAccountDisabled() {
		citibank.disabled = true;
		citibank.save();
		
		Response response = GET("/accounts/read/" + citibank.id);
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	}
	
	@Test
	public void testCreateAccount() {
		String body = "{data:{\"name\":\"Banco do Brasil\",\"number\":\"16468\",\"initial\":15.85}}";
	    Response response = POST("/accounts/create", "application/json", body);
		
	    JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
	    JsonNode data = getNode("data", response);
	    assertEquals("Banco do Brasil", data.findValuesAsText("name").get(0));
		assertEquals("16468", data.findValuesAsText("number").get(0));
		assertEquals("15.85", data.findValuesAsText("initial").get(0));
		assertEquals("false", data.findValuesAsText("disabled").get(0));
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
		assertEquals("false", data.findValuesAsText("disabled").get(0));
	}
}

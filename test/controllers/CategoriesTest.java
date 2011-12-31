package controllers;

import models.Category;

import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import play.db.jpa.JPA;
import play.db.jpa.JPABase;
import play.mvc.Http.Response;

public class CategoriesTest extends FixturesAndLogin {
	
	private Category food;
	
	public void setUp() {
		super.setUp();
		food = Category.find("byName", "Food").first();
	}
	
	@Test
	public void testGetAllCategoriesByUser() {
		Response response = GET("/categories/read");
		
		JsonNode data = getNode("data", response);
		assertEquals(10, data.findValuesAsText("user").size());
		assertEquals(10, data.findValuesAsText("key").size());
		assertEquals(10, data.findValuesAsText("name").size());
	}

	@Test
	public void testGetCategoryById() {
		Response response = GET("/categories/read/" + food.id);
		
		JsonNode data = getNode("data", response);
		assertEquals("Food", data.findValuesAsText("name").get(0));
		assertEquals("3", data.findValuesAsText("key").get(0));
		assertEquals("admin@localhost.com", data.findValuesAsText("username").get(0));
	}
	
	@Test
	public void testDeleteByIdAndRemoveDependenciesInTransactions() {
		Response response = DELETE("/categories/delete/" + food.id);
		
		JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
		JsonNode data = getNode("data", response);
		assertEquals("Food", data.findValuesAsText("name").get(0));
		assertEquals("3", data.findValuesAsText("key").get(0));
		assertEquals("admin@localhost.com", data.findValuesAsText("username").get(0));
		
		Category category = Category.find("byName", "Food").first();
		assertNull(category);
	}
	
	/*
	@Test
	public void testCreateCategory() {
		String body = "{data:{\"name\":\"Banco do Brasil\",\"number\":\"16468\",\"initial\":15.85}}";
	    Response response = POST("/categories/create", "application/json", body);
		
	    JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
	    JsonNode data = getNode("data", response);
	    assertEquals("Banco do Brasil", data.findValuesAsText("name").get(0));
		assertEquals("16468", data.findValuesAsText("number").get(0));
		assertEquals("15.85", data.findValuesAsText("initial").get(0));
		assertEquals("false", data.findValuesAsText("disabled").get(0));
	}
	
	@Test
	public void testUpdateCategoryNumberAndInitial() {
		String body = "{data:{\"id\":" + citibank.id + ",\"name\":\"Citibank\",\"number\":\"17599\",\"initial\":85.90}}";
	    Response response = POST("/categories/update", "application/json", body);
		
	    JsonNode success = getNode("success", response);
	    assertEquals("true", success.toString());
	    
	    JsonNode data = getNode("data", response);
	    assertEquals("Citibank", data.findValuesAsText("name").get(0));
		assertEquals("17599", data.findValuesAsText("number").get(0));
		assertEquals("85.9", data.findValuesAsText("initial").get(0));
		assertEquals("false", data.findValuesAsText("disabled").get(0));
	}
	*/
}

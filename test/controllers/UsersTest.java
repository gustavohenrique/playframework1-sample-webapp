package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Account;
import models.User;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import play.mvc.Http;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.Scope;
import play.mvc.Scope.Params;
import play.mvc.Scope.Session;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class UsersTest extends FunctionalTest {

	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("fixtures.yml");
	}
	
	@Test
	public void testAccessDeniedForNonAuthenticatedUsers() {
		Response response = GET("/accounts/read");
		assertFalse(isLogged(response));
	}
	
	@Test
	public void testAccessDeniedForInvalidUser() {
		String body = "{data:{\"username\":\"foo@bar.com\",\"password\":\"xxxxxx\"}}";
		Response response = POST("/users/authenticate", "application/json", body);
		
		assertFalse(isLogged(response));
	}
	
	@Test
	public void testAccessGaranted() {
		
		String body = "{data:{\"username\":\"admin@localhost.com\",\"password\":\"123456\"}}";
		Response response = POST("/users/authenticate", "application/json", body);
		
		assertTrue(isLogged(response));
	}
	
	@Test
	public void testCreateUserOnlyRequiredFields() {
		String body = "{data:{\"username\":\"eu@gustavohenrique.net\",\"password\":\"123456\"}}";
	    Response response = POST("/users/create", "application/json", body);		

		assertTrue(isSucccess(response));
		
		User user = User.find("byUsername", "eu@gustavohenrique.net").first();
		assertNotNull(user);
	}
	
	@Test
	public void testFailTryingDuplicateUsername() {
		String body = "{data:{\"username\":\"admin@localhost.com\",\"password\":\"123456\"}}";
		Response response = POST("/users/create", "application/json", body);

		assertFalse(isSucccess(response));
		assertEquals(1, User.find("byUsername", "admin@localhost.com").fetch().size());
	}
	
	private boolean isLogged(Response response) {
		String expected = "c3477dd38d89ed0eb1080c867f8b9cee04a41aab-%00token%3Aadmin%40localhost.com-bc256625e0963e3cfa9cd7d91268529b903f4b3a%00";
		String sessionValue = response.cookies.get("PLAY_SESSION").value;
		return sessionValue.equals(expected);
	}
	
	private boolean isSucccess(Response response) {
		try {
			String json = getContent(response).toString();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode firstNode = mapper.readTree(json).findValue("success");
			return "true".equals(firstNode.toString());
		}
		catch (Exception e) {
			return false;
		}
	}
}

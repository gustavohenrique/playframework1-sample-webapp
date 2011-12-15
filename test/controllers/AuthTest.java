package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Account;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import play.mvc.Http;
import play.mvc.Http.Response;
import play.mvc.Scope;
import play.mvc.Scope.Params;
import play.mvc.Scope.Session;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class AuthTest extends FunctionalTest {

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
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", "foo@bar.com");
		parameters.put("password", "xxxxxxx");
		
		Response response = POST("/auth/authenticate", parameters);
		assertFalse(isLogged(response));
	}
	
	@Test
	public void testAccessGaranted() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", "admin@localhost.com");
		parameters.put("password", "123456");
		
		Response response = POST("/auth/authenticate", parameters);

		assertTrue(isLogged(response));
	}
	
	private boolean isLogged(Response response) {
		String expected = "c3477dd38d89ed0eb1080c867f8b9cee04a41aab-%00token%3Aadmin%40localhost.com-bc256625e0963e3cfa9cd7d91268529b903f4b3a%00";
		String sessionValue = response.cookies.get("PLAY_SESSION").value;
		return sessionValue.equals(expected);
	}
}
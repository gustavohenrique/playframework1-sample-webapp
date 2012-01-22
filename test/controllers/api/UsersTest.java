package controllers.api;

import java.util.HashMap;
import java.util.Map;

import models.User;

import org.junit.Before;
import org.junit.Test;

import play.mvc.Http.Response;
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
		Response response = GET("/application/index");
		assertFalse(isUserAuthenticatedIn(response));
	}
	
	@Test
	public void testAccessDeniedForInvalidUser() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", "foo@bar.com");
		parameters.put("password", "xxx");
		
		Response response = POST("/users/authenticate", parameters);
		
		assertFalse(isUserAuthenticatedIn(response));
	}
	
	@Test
	public void testAccessGaranted() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", "admin@localhost.com");
		parameters.put("password", "123456");
		
		Response response = POST("/users/authenticate", parameters);
		assertEquals("200", response.status.toString());

		assertTrue(isUserAuthenticatedIn(response));
	}
	
	@Test
	public void testCreateUserOnlyRequiredFields() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("user.username", "eu@gustavohenrique.net");
		parameters.put("user.password", "123456");
		
	    Response response = POST("/users/create", parameters);
	    assertEquals("302", response.status.toString());

		User user = User.find("byUsername", "eu@gustavohenrique.net").first();
		assertNotNull(user);
	}
	
	@Test
	public void testFailTryingDuplicateUsername() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("user.username", "admin@localhost.com");
		parameters.put("user.password", "123456");
		
		POST("/users/create", parameters);

		assertEquals(1, User.find("byUsername", "admin@localhost.com").fetch().size());
	}
	
	private boolean isUserAuthenticatedIn(Response response) {
		return "true".equals(getContent(response));
	}
	
}

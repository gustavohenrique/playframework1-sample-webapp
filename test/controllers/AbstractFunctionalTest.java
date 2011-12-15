package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Account;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;

import play.db.jpa.JPAPlugin;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

public abstract class AbstractFunctionalTest extends FunctionalTest {
	
	protected Account citibank;
	
	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("fixtures.yml");
		citibank = Account.find("byName", "Citibank").first();
		login();
	}
	
	protected void login() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("username", "admin@localhost.com");
		parameters.put("password", "123456");
		
		POST("/auth/authenticate", parameters);
	}
	
	protected int getTotalObjectsIn(Response response) {
		try {
			String json = getContent(response).toString();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode firstNode = mapper.readTree(json).findValue("data");
			return firstNode.size();
		}
		catch (Exception e) {
			return 0;
		}
	}

}

package controllers;

import models.Account;
import models.Category;
import models.Payee;

import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPAPlugin;

import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.Scope;
import play.mvc.Scope.Params;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class AccountsTest extends FunctionalTest {

	private Account citibank;
	
	@Before
	public void setUp() {
		JPAPlugin.startTx(false);
		Fixtures.deleteAllModels();
		Fixtures.loadModels("fixtures.yml");
		citibank = Account.find("byName", "Citibank").first();
	}
	
	@Test
	public void testGetAccounts() {
	    String expected = "{\"total\":2,\"data\":[{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":1},{\"key\":2,\"name\":\"Itau\",\"number\":\"6254\",\"id\":2}],\"success\":true}";
		
	    Response response = GET("/accounts/read");
		assertEquals(expected, response.out.toString());
	}
	
//	@Test
//	public void testUpdateAccount() {
//	    String expected = "";
//		Request req = newRequest();
//		req.contentType = "application/json";
//		req.params.put("id", "1");
//		req.actionMethod = "PUT";
//		
//	    Response response = POST(req, "/accounts/update/1");//, "application/json", "{\"id\":1,\"name\":\"Cheque Account2\",\"number\":\"01548726554\",\"initial\":76.22}");
//		assertEquals(expected, response.out.toString());
//	}
}

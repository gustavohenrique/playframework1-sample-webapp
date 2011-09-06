package controllers;

import models.Account;

import org.junit.Before;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class TransactionsTest extends FunctionalTest {

	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("fixtures.yml");
	}
	
	@Test
	public void testGetAllAccounts() {
	    String expected = "{\"data\":[{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":1},{\"key\":2,\"name\":\"Itau\",\"number\":\"6254\",\"id\":2}],\"success\":\"true\"}";
		Response response = GET("/transactions/accounts");
		assertEquals(expected, response.out.toString());
	}
}

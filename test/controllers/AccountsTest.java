package controllers;

import org.junit.Test;

import play.mvc.Http.Response;

public class AccountsTest extends AbstractFunctionalTest {
	
	@Test
	public void testGetAllAccounts() {
		Response response = GET("/accounts/read");
		assertEquals(2, getTotalObjectsIn(response));
	}
	
//	@Test
//	public void testGetAccountById() {
//		Response response = GET("/accounts/read/1");
//		assertEquals(1, getTotalObjectsIn(response));
//	}
	
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

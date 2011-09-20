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
	public void testGetAccounts() {
	    String expected = "{\"data\":[{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":1},{\"key\":2,\"name\":\"Itau\",\"number\":\"6254\",\"id\":2}],\"success\":\"true\"}";
		Response response = GET("/transactions/accounts");
		assertEquals(expected, response.out.toString());
	}
	
	@Test
	public void testGetTransactionsAccordingOfAccount() {
		String expected = "{\"data\":[{\"description\":\"Compra do mes\",\"transactionDate\":\"Jul 31, 2011\",\"amount\":-1000.00,\"balance\":-1000.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":3},\"payee\":{\"key\":0,\"name\":\"Wallmart\",\"id\":10},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":11},\"payment\":\"4\",\"id\":10},{\"description\":\"Salario\",\"transactionDate\":\"Aug 1, 2011\",\"amount\":5000.00,\"balance\":4000.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":3},\"payee\":{\"key\":0,\"name\":\"My Company\",\"id\":18},\"category\":{\"key\":21,\"parent\":0,\"name\":\"Salary\",\"id\":16},\"payment\":\"3\",\"id\":11},{\"description\":\"Mensalidade internet\",\"transactionDate\":\"Aug 2, 2011\",\"amount\":-100.00,\"balance\":3900.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":3},\"payee\":{\"key\":0,\"name\":\"Net Virtua\",\"id\":17},\"category\":{\"key\":19,\"parent\":0,\"name\":\"Internet\",\"id\":18},\"payment\":\"5\",\"id\":12},{\"description\":\"Almoco\",\"transactionDate\":\"Aug 3, 2011\",\"amount\":-10.00,\"balance\":3890.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":3},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":11},\"payment\":\"4\",\"id\":13},{\"description\":\"Chocolate\",\"transactionDate\":\"Aug 4, 2011\",\"amount\":-50.00,\"balance\":3840.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":3},\"payee\":{\"key\":0,\"name\":\"Cacau Show\",\"id\":15},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":11},\"payment\":\"5\",\"id\":14},{\"description\":\"Compras\",\"transactionDate\":\"Aug 5, 2011\",\"amount\":-100.00,\"balance\":3740.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":3},\"payee\":{\"key\":0,\"name\":\"Hortifruti\",\"id\":16},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":11},\"payment\":\"5\",\"id\":15},{\"description\":\"TV LED\",\"transactionDate\":\"Aug 7, 2011\",\"amount\":-2000.00,\"balance\":1740.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":3},\"payee\":{\"key\":0,\"name\":\"Americanas\",\"id\":14},\"category\":{\"key\":11,\"parent\":0,\"name\":\"Others\",\"id\":20},\"payment\":\"5\",\"id\":17}],\"success\":\"true\"}";
		Response response = GET("/transactions/filter?accountId=1");
		assertEquals(expected, response.out.toString());
	}
}

package controllers;

import models.Account;
import models.Category;
import models.Payee;

import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPAPlugin;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class TransactionsTest extends FunctionalTest {

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
		
	    Response response = GET("/transactions/accounts");
		assertEquals(expected, response.out.toString());
	}
	
	@Test
	public void testGetCategories() {
	    String expected = "{\"total\":10,\"data\":[{\"key\":10,\"parent\":0,\"name\":\"Book\",\"id\":19},{\"key\":22,\"parent\":0,\"name\":\"Clothes\",\"id\":15},{\"key\":1,\"parent\":0,\"name\":\"Education\",\"id\":17},{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":11},{\"key\":2,\"parent\":0,\"name\":\"Helth\",\"id\":12},{\"key\":19,\"parent\":0,\"name\":\"Internet\",\"id\":18},{\"key\":23,\"parent\":0,\"name\":\"Investment\",\"id\":14},{\"key\":11,\"parent\":0,\"name\":\"Others\",\"id\":20},{\"key\":21,\"parent\":0,\"name\":\"Salary\",\"id\":16},{\"key\":11,\"parent\":0,\"name\":\"Travel\",\"id\":13}],\"success\":true}";
		
	    Response response = GET("/transactions/categories");
		assertEquals(expected, response.out.toString());
	}
	
	@Test
	public void testGetPayees() {
	    String expected = "{\"total\":9,\"data\":[{\"key\":0,\"name\":\"Americanas\",\"id\":23},{\"key\":0,\"name\":\"Cacau Show\",\"id\":24},{\"key\":0,\"name\":\"Cinemark\",\"id\":20},{\"key\":0,\"name\":\"Hering\",\"id\":21},{\"key\":0,\"name\":\"Hortifruti\",\"id\":25},{\"key\":0,\"name\":\"My Company\",\"id\":27},{\"key\":0,\"name\":\"Net Virtua\",\"id\":26},{\"key\":0,\"name\":\"Ricardo Eletro\",\"id\":22},{\"key\":0,\"name\":\"Wallmart\",\"id\":19}],\"success\":true}";
		
	    Response response = GET("/transactions/payees");
		assertEquals(expected, response.out.toString());
	}
	
	@Test
	public void testGetTransactionsAccordingOfAccount() {
		String expected = "{\"total\":9,\"data\":[{\"description\":\"Compra do mes\",\"transactionDate\":\"Jul 31, 2011\",\"amount\":-1000.00,\"balance\":-1000.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":7},\"payee\":{\"key\":0,\"name\":\"Wallmart\",\"id\":28},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":31},\"payment\":\"4\",\"id\":28},{\"description\":\"Salario\",\"transactionDate\":\"Aug 1, 2011\",\"amount\":5000.00,\"balance\":4000.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":7},\"payee\":{\"key\":0,\"name\":\"My Company\",\"id\":36},\"category\":{\"key\":21,\"parent\":0,\"name\":\"Salary\",\"id\":36},\"payment\":\"3\",\"id\":29},{\"description\":\"Mensalidade internet\",\"transactionDate\":\"Aug 2, 2011\",\"amount\":-100.00,\"balance\":3900.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":7},\"payee\":{\"key\":0,\"name\":\"Net Virtua\",\"id\":35},\"category\":{\"key\":19,\"parent\":0,\"name\":\"Internet\",\"id\":38},\"payment\":\"5\",\"id\":30},{\"description\":\"Almoco\",\"transactionDate\":\"Aug 3, 2011\",\"amount\":-10.00,\"balance\":3890.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":7},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":31},\"payment\":\"4\",\"id\":31},{\"description\":\"Chocolate\",\"transactionDate\":\"Aug 4, 2011\",\"amount\":-50.00,\"balance\":3840.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":7},\"payee\":{\"key\":0,\"name\":\"Cacau Show\",\"id\":33},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":31},\"payment\":\"5\",\"id\":32},{\"description\":\"Compras\",\"transactionDate\":\"Aug 5, 2011\",\"amount\":-100.00,\"balance\":3740.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":7},\"payee\":{\"key\":0,\"name\":\"Hortifruti\",\"id\":34},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":31},\"payment\":\"5\",\"id\":33},{\"description\":\"TV LED\",\"transactionDate\":\"Aug 7, 2011\",\"amount\":-2000.00,\"balance\":1740.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":7},\"payee\":{\"key\":0,\"name\":\"Americanas\",\"id\":32},\"category\":{\"key\":11,\"parent\":0,\"name\":\"Others\",\"id\":40},\"payment\":\"5\",\"id\":35}],\"success\":true}";
		
		Response response = GET("/transactions/filter/" + citibank.getId());
		assertEquals(expected, response.out.toString());
	}
	
	@Test
	public void testGetTransactionsPaginated() {
		String expected = "{\"total\":9,\"data\":[{\"description\":\"Salario\",\"transactionDate\":\"Aug 1, 2011\",\"amount\":5000.00,\"balance\":4000.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":9},\"payee\":{\"key\":0,\"name\":\"My Company\",\"id\":45},\"category\":{\"key\":21,\"parent\":0,\"name\":\"Salary\",\"id\":46},\"payment\":\"3\",\"id\":38},{\"description\":\"Mensalidade internet\",\"transactionDate\":\"Aug 2, 2011\",\"amount\":-100.00,\"balance\":3900.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":9},\"payee\":{\"key\":0,\"name\":\"Net Virtua\",\"id\":44},\"category\":{\"key\":19,\"parent\":0,\"name\":\"Internet\",\"id\":48},\"payment\":\"5\",\"id\":39}],\"success\":true}";
		
		Response response = GET("/transactions/filter?accountId=" + citibank.getId() + "&start=1&limit=2");
		assertEquals(expected, response.out.toString());
	}
	
	@Test
	public void testGetTransactionsFilteredByCategory() {
		Category category = Category.find("byName", "Food").first();
		String expected = "{\"total\":9,\"data\":[{\"description\":\"Compra do mes\",\"transactionDate\":\"Jul 31, 2011\",\"amount\":-1000.00,\"balance\":-1000.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":11},\"payee\":{\"key\":0,\"name\":\"Wallmart\",\"id\":46},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":51},\"payment\":\"4\",\"id\":46},{\"description\":\"Almoco\",\"transactionDate\":\"Aug 3, 2011\",\"amount\":-10.00,\"balance\":3890.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":11},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":51},\"payment\":\"4\",\"id\":49},{\"description\":\"Chocolate\",\"transactionDate\":\"Aug 4, 2011\",\"amount\":-50.00,\"balance\":3840.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":11},\"payee\":{\"key\":0,\"name\":\"Cacau Show\",\"id\":51},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":51},\"payment\":\"5\",\"id\":50},{\"description\":\"Compras\",\"transactionDate\":\"Aug 5, 2011\",\"amount\":-100.00,\"balance\":3740.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":11},\"payee\":{\"key\":0,\"name\":\"Hortifruti\",\"id\":52},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":51},\"payment\":\"5\",\"id\":51}],\"success\":true}";
		
		Response response = GET("/transactions/filter?accountId=" + citibank.getId()+"&category=" + category.getId());
		assertEquals(expected, response.out.toString());
	}
	
	@Test
	public void testGetTransactionsFilteredByPayee() {
		Payee payee = Payee.find("byName", "Americanas").first();
		String expected = "{\"total\":9,\"data\":[{\"description\":\"TV LED\",\"transactionDate\":\"Aug 7, 2011\",\"amount\":-2000.00,\"balance\":1740.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":13},\"payee\":{\"key\":0,\"name\":\"Americanas\",\"id\":59},\"category\":{\"key\":11,\"parent\":0,\"name\":\"Others\",\"id\":70},\"payment\":\"5\",\"id\":62}],\"success\":true}";
		
		Response response = GET("/transactions/filter?accountId=" + citibank.getId()+"&payee=" + payee.getId());
		assertEquals(expected, response.out.toString());
	}
	
	@Test
	public void testGetTransactionsFilteredByStartDateUntilNow() {
		String expected = "{\"total\":9,\"data\":[{\"description\":\"TV LED\",\"transactionDate\":\"Aug 7, 2011\",\"amount\":-2000.00,\"balance\":1740.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":15},\"payee\":{\"key\":0,\"name\":\"Americanas\",\"id\":68},\"category\":{\"key\":11,\"parent\":0,\"name\":\"Others\",\"id\":80},\"payment\":\"5\",\"id\":71}],\"success\":true}";
		
		Response response = GET("/transactions/filter?accountId=" + citibank.getId()+"&startDate=2011-08-06");
		assertEquals(expected, response.out.toString());
	}
	
	@Test
	public void testGetTransactionsFilteredByEndDate() {
		String expected = "{\"total\":9,\"data\":[{\"description\":\"Compra do mes\",\"transactionDate\":\"Jul 31, 2011\",\"amount\":-1000.00,\"balance\":-1000.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":17},\"payee\":{\"key\":0,\"name\":\"Wallmart\",\"id\":73},\"category\":{\"key\":3,\"parent\":0,\"name\":\"Food\",\"id\":81},\"payment\":\"4\",\"id\":73},{\"description\":\"Salario\",\"transactionDate\":\"Aug 1, 2011\",\"amount\":5000.00,\"balance\":4000.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":17},\"payee\":{\"key\":0,\"name\":\"My Company\",\"id\":81},\"category\":{\"key\":21,\"parent\":0,\"name\":\"Salary\",\"id\":86},\"payment\":\"3\",\"id\":74},{\"description\":\"Mensalidade internet\",\"transactionDate\":\"Aug 2, 2011\",\"amount\":-100.00,\"balance\":3900.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":17},\"payee\":{\"key\":0,\"name\":\"Net Virtua\",\"id\":80},\"category\":{\"key\":19,\"parent\":0,\"name\":\"Internet\",\"id\":88},\"payment\":\"5\",\"id\":75}],\"success\":true}";
		
		Response response = GET("/transactions/filter?accountId=" + citibank.getId()+"&endDate=2011-08-02");
		assertEquals(expected, response.out.toString());
	}
	
	@Test
	public void testGetTransactionsFilteredByDateInterval() {
		String expected = "{\"total\":9,\"data\":[{\"description\":\"Mensalidade internet\",\"transactionDate\":\"Aug 2, 2011\",\"amount\":-100.00,\"balance\":3900.00,\"account\":{\"key\":1,\"name\":\"Citibank\",\"number\":\"25739904721\",\"id\":19},\"payee\":{\"key\":0,\"name\":\"Net Virtua\",\"id\":89},\"category\":{\"key\":19,\"parent\":0,\"name\":\"Internet\",\"id\":98},\"payment\":\"5\",\"id\":84}],\"success\":true}";
		
		Response response = GET("/transactions/filter?accountId=" + citibank.getId()+"&startDate=2011-08-02&endDate=2011-08-02");
		assertEquals(expected, response.out.toString());
	}
}

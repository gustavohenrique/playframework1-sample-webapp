package controllers;

import models.Account;
import models.Category;
import models.Payee;

import org.junit.Test;

import play.mvc.Http.Response;

public class TransactionsTest extends FixturesAndLogin {

	private Account citibank;
	
	public void setUp() {
		super.setUp();
		citibank = Account.find("byName", "Citibank").first();
	}
	
	@Test
	public void testGetCategories() {
	    Response response = GET("/transactions/categories");
		assertEquals(10, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetPayees() {
	    Response response = GET("/transactions/payees");
	    assertEquals(9, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsAccordingOfAccount() {
		Response response = GET("/transactions/read/" + citibank.getId());
		assertEquals(7, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsPaginated() {
		Response response = GET("/transactions/read?accountId=" + citibank.getId() + "&start=1&limit=2");
		assertEquals(2, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsFilteredByCategory() {
		Category category = Category.find("byName", "Food").first();
		Response response = GET("/transactions/read?accountId=" + citibank.getId()+"&category=" + category.getId());
		assertEquals(4, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsFilteredByPayee() {
		Payee payee = Payee.find("byName", "Americanas").first();
		Response response = GET("/transactions/read?accountId=" + citibank.getId()+"&payee=" + payee.getId());
		assertEquals(1, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsFilteredByStartDateUntilNow() {
		Response response = GET("/transactions/read?accountId=" + citibank.getId()+"&startDate=2011-08-06");
		assertEquals(1, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsFilteredByEndDate() {
		Response response = GET("/transactions/read?accountId=" + citibank.getId()+"&endDate=2011-08-02");
		assertEquals(3, getTotalObjectsIn(response));
	}
	
	@Test
	public void testGetTransactionsFilteredByDateInterval() {
		Response response = GET("/transactions/read?accountId=" + citibank.getId()+"&startDate=2011-08-02&endDate=2011-08-02");
		assertEquals(1, getTotalObjectsIn(response));
	}
	
}

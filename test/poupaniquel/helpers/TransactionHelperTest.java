package poupaniquel.helpers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.Account;
import models.Category;
import models.Payee;
import models.Transaction;

import org.junit.Before;
import org.junit.Test;

import play.db.jpa.GenericModel.JPAQuery;
import play.db.jpa.JPABase;
import play.test.Fixtures;
import play.test.UnitTest;

public class TransactionHelperTest extends UnitTest {

	private Account citibank;
	
	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("fixtures.yml");
		citibank = Account.find("byName", "Citibank").first();
	}
	
	@Test
	public void testGetTransactionsWithCalculatedBalance() {
		
		TransactionHelper helper = new TransactionHelper();
		List<Transaction> transactions = helper.getByAccount(citibank);
	
		assertEquals("-1000.00", transactions.get(0).getBalance().toString());
		assertEquals("4000.00", transactions.get(1).getBalance().toString());
		assertEquals("3900.00", transactions.get(2).getBalance().toString());
		assertEquals("3890.00", transactions.get(3).getBalance().toString());
		assertEquals("3840.00", transactions.get(4).getBalance().toString());
		assertEquals("3740.00", transactions.get(5).getBalance().toString());
		assertEquals("1740.00", transactions.get(6).getBalance().toString());
	}
	
	@Test
	public void testGetTransactionsWithCalculatedBalanceOnDateInterval() {
		Calendar start = Calendar.getInstance();
		start.set(2011, Calendar.AUGUST, 5);
		
		Calendar end = Calendar.getInstance();
		end.set(2011, Calendar.AUGUST, 8);
		
		TransactionHelper helper = new TransactionHelper();
		helper.setStart(start.getTime());
		helper.setEnd(end.getTime());
		
		List<Transaction> transactions = helper.getByAccount(citibank);
		
		assertEquals(2, transactions.size());
//      Verificar bug do play que adiciona 1 dia na data da fixture		
//		assertEquals("3740.00", transactions.get(0).getBalance().toString());
//		assertEquals("1740.00", transactions.get(1).getBalance().toString());
	}
	
	@Test
	public void testGetTransactionsFilteredByPayee() {
		Payee americanas = Payee.find("byName", "Americanas").first();
		
		TransactionHelper helper = new TransactionHelper();
		helper.setPayee(americanas);
		
		List<Transaction> transactions = helper.getByAccount(citibank);
	
		assertEquals("-2000.00", transactions.get(0).getBalance().toString());
	}
	
	@Test
	public void testGetTransactionsFilteredByCategory() {
		Category food = Category.find("byName", "Food").first();
		
		TransactionHelper helper = new TransactionHelper();
		helper.setCategory(food);
		
		List<Transaction> transactions = helper.getByAccount(citibank);
	
		assertEquals(4, transactions.size());
	}
	
}

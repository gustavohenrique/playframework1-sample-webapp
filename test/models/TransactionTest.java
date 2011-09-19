package models;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class TransactionTest extends UnitTest {

	private Account citibank;
	
	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("fixtures.yml");
		citibank = Account.find("byName", "Citibank").first();
	}
	
	@Test
	public void testGetTransactionsWithCalculatedBalance() {
		
		List<Transaction> transactions = Transaction.filter(citibank);
	
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
		
		List<Transaction> transactions = Transaction.filter(citibank, start.getTime(), end.getTime());
		
		assertEquals(2, transactions.size());
//      Verificar bug do play que adiciona 1 dia na data da fixture		
//		assertEquals("3740.00", transactions.get(0).getBalance().toString());
//		assertEquals("1740.00", transactions.get(1).getBalance().toString());
	}
	
	@Test
	public void testGetTransactionsFilteredByPayee() {
		Payee americanas = Payee.find("byName", "Americanas").first();
		
		List<Transaction> transactions = Transaction.filter(citibank, americanas);
	
		assertEquals("-2000.00", transactions.get(0).getBalance().toString());
	}
	
	@Test
	public void testGetTransactionsFilteredByCategory() {
		Category food = Category.find("byName", "Food").first();
		List<Transaction> transactions = Transaction.filter(citibank, food);
	
		assertEquals(4, transactions.size());
	}
	
}

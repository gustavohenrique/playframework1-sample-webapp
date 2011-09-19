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
	public void testShouldReturnNullIfNotSentAccount() {
		List<Transaction> transactions = Transaction.filter(null, null, null, null, null);
		assertNull(transactions);
	}
	
	@Test
	public void testGetTransactionsWithCalculatedBalanceByAccount() {
		
		List<Transaction> transactions = Transaction.filterByAccount(citibank);
	
		assertEquals("-1000.00", transactions.get(0).getBalance().toString());
		assertEquals("4000.00", transactions.get(1).getBalance().toString());
		assertEquals("3900.00", transactions.get(2).getBalance().toString());
		assertEquals("3890.00", transactions.get(3).getBalance().toString());
		assertEquals("3840.00", transactions.get(4).getBalance().toString());
		assertEquals("3740.00", transactions.get(5).getBalance().toString());
		assertEquals("1740.00", transactions.get(6).getBalance().toString());
	}
	
	@Test
	public void testGetTransactionsByDateInterval() {
		Calendar start = Calendar.getInstance();
		start.set(2011, Calendar.AUGUST, 5);
		
		Calendar end = Calendar.getInstance();
		end.set(2011, Calendar.AUGUST, 8);
		
		List<Transaction> transactions = Transaction.filterByDateInterval(citibank, start.getTime(), end.getTime());
		
		assertEquals(2, transactions.size());
//      Verificar bug do play que adiciona 1 dia na data da fixture		
//		assertEquals("3740.00", transactions.get(0).getBalance().toString());
//		assertEquals("1740.00", transactions.get(1).getBalance().toString());
	}
	
	@Test
	public void testGetTransactionsByPayee() {
		Payee americanas = Payee.find("byName", "Americanas").first();
		
		List<Transaction> transactions = Transaction.filterByPayee(citibank, americanas);
	
		assertEquals("-2000.00", transactions.get(0).getBalance().toString());
	}
	
	@Test
	public void testGetTransactionsByCategory() {
		Category food = Category.find("byName", "Food").first();
		List<Transaction> transactions = Transaction.filterByCategory(citibank, food);
	
		assertEquals(4, transactions.size());
	}
	
}

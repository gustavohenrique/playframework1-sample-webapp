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
	public void testShouldReturnNullIfDontHaveAccount() {
		List<Transaction> transactions = Transaction.filter(null);
		assertNull(transactions);
	}
	
	@Test
	public void testGetTransactionsByDateInterval() {
		Calendar start = Calendar.getInstance();
		start.set(2011, Calendar.AUGUST, 5);
		
		Calendar end = Calendar.getInstance();
		end.set(2011, Calendar.AUGUST, 8);
		
		List<Transaction> transactions = Transaction.filterByDateInterval(citibank, start.getTime(), end.getTime());
		
		assertEquals(2, transactions.size());
		assertEquals("-100.00", transactions.get(0).amount.toString());
		assertEquals("-2000.00", transactions.get(1).amount.toString());
	}
	
	@Test
	public void testGetTransactionsByPayee() {
		Payee americanas = Payee.find("byName", "Americanas").first();
		
		List<Transaction> transactions = Transaction.filterByPayee(citibank, americanas);
	
		assertEquals("-2000.00", transactions.get(0).amount.toString());
	}
	
	@Test
	public void testGetTransactionsByCategory() {
		Category food = Category.find("byName", "Food").first();
		List<Transaction> transactions = Transaction.filterByCategory(citibank, food);
	
		assertEquals(4, transactions.size());
	}
	
	@Test
	public void testGetTransactionsByPayeeAndCategory() {
		Payee cacau = Payee.find("byName", "Cacau Show").first();
		Category food = Category.find("byName", "Food").first();
		
		TransactionFilterOptions options = new TransactionFilterOptions();
		options.setAccount(citibank);
		options.setCategory(food);
		options.setPayee(cacau);
		List<Transaction> transactions = Transaction.filter(options);
	
		assertEquals("-50.00", transactions.get(0).amount.toString());
	}
	
	@Test
	public void testGetTransactionsByPayeeAndCategoryPaginated() {
		TransactionFilterOptions options = new TransactionFilterOptions();
		options.setAccount(citibank);
		options.setPage(0, 2);
		List<Transaction> transactions = Transaction.filter(options);
	
		assertEquals(2,  transactions.size());
		assertEquals("-1000.00", transactions.get(0).amount.toString());
		assertEquals("5000.00", transactions.get(1).amount.toString());
	}
	
}

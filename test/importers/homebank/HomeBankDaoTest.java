package importers.homebank;

import java.math.BigDecimal;

import models.Account;
import models.Category;
import models.HomeBank;
import models.Payee;
import models.Transaction;
import models.User;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class HomeBankDaoTest extends UnitTest {

	private User user;
	
	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("fixtures.yml");
		user = User.find("byUsername", "admin@localhost.com").first();
	}
	
	@Test
	public void testPersistTransactionRecursivelyExceptUser() {
		Account account = new Account();
		account.key = 1;
		account.name = "Citibank";
		account.number = "1536-4";
		
		Category category = new Category();
		category.key = 3;
		category.name = "Food";
		
		Payee payee = new Payee();
		payee.key = 1;
		payee.name = "Wallmart";

		Transaction transaction = new Transaction();
		transaction.user = user;
		transaction.amount = new BigDecimal("-15.00");
		transaction.description = "KitKat Chocolate";
		transaction.account = account;
		transaction.category = category;
		transaction.payee = payee;
		transaction.payment = "4";
		
		HomeBank homeBank = new HomeBank();
		homeBank.setVersion("0.5");
		homeBank.setUser(user);
		homeBank.addAccount(account);
		homeBank.addCategory(category);
		homeBank.addPayee(payee);
		homeBank.addTransaction(transaction);
		
		try {
			new HomeBankDao().persist(homeBank);
			
			Transaction persisted = Transaction.find("byDescription", "KitKat Chocolate").first();
			assertEquals("Citibank", persisted.account.name);
			assertEquals("Food", persisted.category.name);
			assertEquals("Wallmart", persisted.payee.name);
			assertEquals("-15.00", persisted.amount.toString());
			
			assertEquals("admin@localhost.com", persisted.account.user.username);
			assertEquals("admin@localhost.com", persisted.category.user.username);
			assertEquals("admin@localhost.com", persisted.payee.user.username);
			assertEquals("admin@localhost.com", persisted.user.username);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testPersistNewTransaction() {
		Account account = Account.find("byName", "Citibank").first();
		Category category = Category.find("byName", "Food").first();
		Payee payee = Payee.find("byName", "Cinemark").first();

		Transaction transaction = new Transaction();
		transaction.amount = new BigDecimal("189");
		transaction.description = "Dinner and fun";
		transaction.account = account;
		transaction.category = category;
		transaction.payee = payee;
		transaction.payment = "3";
		
		HomeBank homeBank = new HomeBank();
		homeBank.setVersion("0.5");
		homeBank.setUser(user);
		homeBank.addTransaction(transaction);
		
		try {
			new HomeBankDao().persist(homeBank);
			
			Transaction persisted = Transaction.find("byDescription", "Dinner and fun").first();
			assertEquals("189", persisted.amount.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}

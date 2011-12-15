package poupaniquel.importers.homebank;

import java.math.BigDecimal;

import models.Account;
import models.Category;
import models.HomeBank;
import models.Payee;
import models.Transaction;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class HomeBankDaoTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("fixtures.yml");
	}
	
	@Test
	public void testPersistTransactionInEmptyDatabase() {
		Account account = new Account();
		account.key = 1;
		account.name = "Citibank";
		account.number = "1536-4";
		
		Category category = new Category();
		category.key = 1;
		category.name = "Food";
		
		Payee payee = new Payee();
		payee.key = 1;
		payee.name = "Wallmart";

		Transaction transaction = new Transaction();
		transaction.amount = BigDecimal.TEN;
		transaction.description = "Compra do mes";
		transaction.account = account;
		transaction.category = category;
		transaction.payee = payee;
		transaction.payment = "4";
		
		HomeBank homeBank = new HomeBank();
		homeBank.setVersion("0.5");
		homeBank.addAccount(account);
		homeBank.addCategory(category);
		homeBank.addPayee(payee);
		homeBank.addTransaction(transaction);
		
		try {
			new HomeBankDao().persist(homeBank);
			
			Transaction persistedTransaction = Transaction.findById(1l);
			assertEquals(account.name, persistedTransaction.account.name);
			assertEquals(category.name, persistedTransaction.category.name);
			assertEquals(payee.name, persistedTransaction.payee.name);
			assertEquals(transaction.description, persistedTransaction.description);
			assertEquals("-1000.00", persistedTransaction.amount.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testPersistTransactionWhenAlreadyExistsOthers() {
		Account account = Account.find("byName", "Citibank").first();
		Category category = Category.find("byName", "Food").first();
		Payee payee = Payee.find("byName", "Cinemark").first();

		Transaction transaction = new Transaction();
		transaction.amount = new BigDecimal("200");
		transaction.description = "Launch and fun";
		transaction.account = account;
		transaction.category = category;
		transaction.payee = payee;
		transaction.payment = "3";
		
		HomeBank homeBank = new HomeBank();
		homeBank.setVersion("0.5");
		homeBank.addTransaction(transaction);
		
		try {
			new HomeBankDao().persist(homeBank);
			
			Transaction persistedTransaction = Transaction.find("byDescription", "Launch and fun").first();
			assertEquals("200", persistedTransaction.amount.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}

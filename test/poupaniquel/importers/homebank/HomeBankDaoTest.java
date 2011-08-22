package poupaniquel.importers.homebank;

import java.math.BigDecimal;

import models.Account;
import models.Category;
import models.HomeBank;
import models.Payee;
import models.Transaction;

import org.junit.Test;

import play.test.UnitTest;

public class HomeBankDaoTest extends UnitTest {

	@Test
	public void testPersistHomeBankData() {
		Account account = new Account();
		account.setKey(1);
		account.setName("Citibank");
		account.setNumber("1536-4");
		
		Category category = new Category();
		category.setKey(1);
		category.setName("Internet");
		
		Payee payee = new Payee();
		payee.setKey(1);
		payee.setName("Net");

		Transaction transaction = new Transaction();
		transaction.setAmount(BigDecimal.TEN);
		transaction.setDescription("Internet service provider");
		transaction.setAccount(account);
		transaction.setCategory(category);
		transaction.setPayee(payee);
		transaction.setPayment("4");
		
		HomeBank homeBank = new HomeBank();
		homeBank.setVersion("0.5");
		homeBank.addAccount(account);
		homeBank.addCategory(category);
		homeBank.addPayee(payee);
		homeBank.addTransaction(transaction);
		
		try {
			new HomeBankDao().persist(homeBank);
			
			Transaction persistedTransaction = Transaction.findById(1l);
			assertEquals(account.getName(), persistedTransaction.getAccount().getName());
			assertEquals(category.getName(), persistedTransaction.getCategory().getName());
			assertEquals(payee.getName(), persistedTransaction.getPayee().getName());
			assertEquals(transaction.getDescription(), persistedTransaction.getDescription());
		}
		catch (Exception e) {
			fail();
		}
	}
	
}

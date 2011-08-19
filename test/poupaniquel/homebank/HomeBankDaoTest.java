package poupaniquel.homebank;

import java.math.BigDecimal;
import java.util.Calendar;

import models.Account;
import models.Category;
import models.HomeBank;
import models.Payee;
import models.Payment;
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
		
		Payment payment = new Payment();
		payment.setKey(1);
		payment.setName("CreditCard");
		
		
		Transaction transaction = new Transaction();
		transaction.setAmount(BigDecimal.TEN);
		transaction.setDescription("Internet service provider");
		transaction.setCategory(category);
		transaction.setPayee(payee);
		transaction.setPayment(payment);
		
		HomeBank homeBank = new HomeBank();
		homeBank.setVersion("0.5");
		homeBank.addAccount(account);
		homeBank.addCategory(category);
		homeBank.addPayee(payee);
		
		try {
			new HomeBankDao().persist(homeBank);
		}
		catch (Exception e) {
			fail();
		}
	}
}

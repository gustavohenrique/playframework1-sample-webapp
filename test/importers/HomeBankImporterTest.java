package importers;

import importers.HomeBankImporter;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import models.Account;
import models.Category;
import models.HomeBank;
import models.Payee;
import models.Transaction;

import org.junit.Before;
import org.junit.Test;

import play.test.UnitTest;
import play.vfs.VirtualFile;

public class HomeBankImporterTest extends UnitTest {
	
	HomeBank homeBank;
	
	@Before
	public void onSetUp() throws Exception {
		InputStream file = VirtualFile.open("test/homebank-sample.xhb").inputstream();
		HomeBankImporter importer = new HomeBankImporter();
		homeBank = importer.fromXml(file);
	}

	@Test
	public void testGetHomeBankVersion() {
		assertEquals("0.5", homeBank.getVersion());
	}
	
	@Test
	public void testImportAllAccounts() {
		List<Account> accounts = homeBank.getAccounts();
		assertEquals(3, accounts.size());
		
		Account account = accounts.get(0);
		assertEquals(1, account.getKey());
		assertEquals("Citibank", account.getName());
		assertEquals("25739904721", account.getNumber());
		assertEquals(new BigDecimal("900.5"), account.getInitial());
	}
	
	@Test
	public void testImportAllPayees() {
		List<Payee> payees = homeBank.getPayees();
		assertEquals(10, payees.size());
		
		Payee payee = payees.get(0);
		assertEquals(1, payee.getKey());
		assertEquals("Wallmart", payee.getName());
	}
	
	@Test
	public void testImportAllCategories() {
		List<Category> categories = homeBank.getCategories();
		assertEquals(10, categories.size());
		
		Category category = categories.get(3);
		assertEquals(10, category.getKey());
		assertEquals(1, category.getParent());
		assertEquals("Book", category.getName());
	}
	
	@Test
	public void testImportAllTransactions() {
		List<Transaction> transactions = homeBank.getTransactions();
		assertEquals(5, transactions.size());
		
		Transaction transaction = transactions.get(0);
		assertEquals(new BigDecimal("-46.47"), transaction.getAmount());
		assertEquals("Buy book Clean Code", transaction.getDescription());
		assertEquals(1, transaction.getAccount().getKey());
		assertEquals(1, transaction.getPayee().getKey());
		assertEquals(10, transaction.getCategory().getKey());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(734138l);
		assertEquals(calendar, transaction.getDate());
	}
}

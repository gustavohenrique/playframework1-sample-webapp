package poupaniquel.importers.homebank;


import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

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
import play.vfs.VirtualFile;

public class HomeBankImporterTest extends UnitTest {
	
	private HomeBank homeBank;
	
	@Before
	public void onSetUp() throws Exception {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("fixtures.yml");
		User user = User.find("byUsername", "admin").first();
		InputStream file = VirtualFile.open("test/homebank-sample.xhb").inputstream();
		HomeBankImporter importer = new HomeBankImporter(user);
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
		assertEquals("admin", account.user);
		assertEquals(1, account.key);
		assertEquals("Citibank", account.name);
		assertEquals("25739904721", account.number);
		assertEquals(new BigDecimal("900.5"), account.initial);
	}
	
	@Test
	public void testImportAllPayees() {
		List<Payee> payees = homeBank.getPayees();
		assertEquals(10, payees.size());
		
		Payee payee = payees.get(0);
		assertEquals("admin", payee.user);
		assertEquals(1, payee.key);
		assertEquals("Wallmart", payee.name);
	}
	
	@Test
	public void testImportAllCategories() {
		List<Category> categories = homeBank.getCategories();
		assertEquals(10, categories.size());
		
		Category category = categories.get(3);
		assertEquals("admin", category.user);
		assertEquals(10, category.key);
		assertEquals(1, category.parent);
		assertEquals("Book", category.name);
	}
	
	@Test
	public void testImportAllTransactions() {
		List<Transaction> transactions = homeBank.getTransactions();
		assertEquals(5, transactions.size());
		
		Transaction transaction = transactions.get(4);
		assertEquals("admin", transaction.user);
		assertEquals(new BigDecimal("-46.47"), transaction.amount);
		assertEquals("Buy book Clean Code", transaction.description);
		assertEquals(1, transaction.account.key);
		assertEquals(1, transaction.payee.key);
		assertEquals(10, transaction.category.key);
		assertEquals("9", transaction.payment);

		// 2011-07-25 = 734343 = 1311562800512
	}

}

package poupaniquel.helpers;

import java.util.ArrayList;
import java.util.List;

import models.Transaction;

import org.junit.Before;
import org.junit.Test;

import play.db.jpa.JPABase;
import play.test.Fixtures;
import play.test.UnitTest;

public class TransactionHelperTest extends UnitTest {

	@Before
	public void setUp() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("fixtures.yml");
	}
	
	@Test
	public void testGetTransactionsWithBalanceCalculate() {
//		List<Transaction> all = Transaction.findAll();
//		List<Transaction> transactions = new TransactionHelper().addBalance(all);
//		
//		account = new Account("citibank");
//		
//		Transaction t1 = new Transaction();
//		t1.setAccount()
//		
//		List<Transaction> expected = new ArrayList<Transaction>(); 
		
	}
	
}

package poupaniquel;

import java.math.BigDecimal;

import models.Account;

public class TestObjects {

	public void deleteAll() {
		Account.deleteAll();
	}
	
	public void create() {
		createAccount(1, "Citibank", "25739904721", new BigDecimal("900.0"));
		createAccount(2, "Itau", "6254", new BigDecimal("900.5"));
	}

	private void createAccount(Integer key, String name, String number, BigDecimal initial) {
		Account account = new Account();
		account.setKey(key);
		account.setName(name);
		account.setNumber(number);
		account.setInitial(initial);
		account.save();
	}
}

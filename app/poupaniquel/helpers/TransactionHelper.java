package poupaniquel.helpers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import models.Account;
import models.Transaction;

public class TransactionHelper {
	
	private Date start = null;
	private Date end = null;

	public List<Transaction> getByAccount(Account account) {
		List<Transaction> all = Transaction.find("byAccount", account).fetch();
		
		List<Transaction> transactionsWithBalance = new ArrayList<Transaction>();
		
		BigDecimal balance = BigDecimal.ZERO;
		
		for (int i = 0; i < all.size(); i++) {
			Transaction transaction = all.get(i);
			BigDecimal amount = transaction.getAmount();
			balance = balance.add(amount);
			transaction.setBalance(balance);
			
			if (isTransactionBetweenInterval(transaction, start, end)) {
				transactionsWithBalance.add(transaction);
			}
		}
		
		return transactionsWithBalance;
	}

	public TransactionHelper between(Date date1, Date date2) {
		start = date1;
		end = date2;
		return this;
	}
	
	private boolean isTransactionBetweenInterval(Transaction transaction, Date start, Date end) {
		return (daysBetween(start, transaction.getTransactionDate()) && daysBetween(transaction.getTransactionDate(), end));
	}
	
	private boolean daysBetween(Date start, Date end) {
		if (! isNull(start) && ! isNull(end)) {
			DateTime d1 = new DateTime(start.getYear(), start.getMonth() + 1, start.getDate(), 0, 0, 0, 0);
			DateTime d2 = new DateTime(end.getYear(), end.getMonth() + 1, end.getDate(), 0, 0, 0, 0);
			Days days = Days.daysBetween(d1, d2);
			
			return days.getDays() >= 0;
		}
		return true;
	}
	
	private boolean isNull(Object object) {
		return object == null;
	}
}

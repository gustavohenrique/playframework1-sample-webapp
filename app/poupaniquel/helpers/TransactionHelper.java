package poupaniquel.helpers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.Days;

import play.db.jpa.JPA;

import models.Account;
import models.Category;
import models.Payee;
import models.Transaction;

public class TransactionHelper {
	
	private Date start;
	private Date end;
	private Payee payee;
	private Category category;
	
	public TransactionHelper() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1984, Calendar.JANUARY, 1);
		start = calendar.getTime();
		end = Calendar.getInstance().getTime();
	}

	public List<Transaction> getByAccount(Account account) {
		
		String sql = "SELECT t FROM transactions t WHERE account_id='" + account.getId() + "' AND transactionDate >= '" + formatDate(start) + "' AND transactionDate <= '" + formatDate(end) + "' ";
		
		if (isNotNull(payee)) {
			sql += "AND payee_id = '" + payee.getId() + "' ";
		}
		
		if (isNotNull(category)) {
			sql += "AND category_id = '" + category.getId() + "' ";
		}
		
		Query query = JPA.em().createQuery(sql);
		List<Transaction> all = query.getResultList(); 
		
		List<Transaction> transactionsWithBalance = new ArrayList<Transaction>();
		
		BigDecimal balance = BigDecimal.ZERO;
		
		for (int i = 0; i < all.size(); i++) {
			Transaction transaction = all.get(i);
			BigDecimal amount = transaction.getAmount();
			balance = balance.add(amount);
			transaction.setBalance(balance);
			
			transactionsWithBalance.add(transaction);
		}
		
		return transactionsWithBalance;
	}

	private String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		return dateFormat.format(date);
	}
	
	private boolean isTransactionBetweenInterval(Transaction transaction, Date start, Date end) {
		return (daysBetween(start, transaction.getTransactionDate()) && daysBetween(transaction.getTransactionDate(), end));
	}
	
	private boolean daysBetween(Date start, Date end) {
		DateTime d1 = new DateTime(start.getYear(), start.getMonth() + 1, start.getDate(), 0, 0, 0, 0);
		DateTime d2 = new DateTime(end.getYear(), end.getMonth() + 1, end.getDate(), 0, 0, 0, 0);
		Days days = Days.daysBetween(d1, d2);
			
		return days.getDays() >= 0;
	}
	
	private boolean isNotNull(Object object) {
		return object != null;
	}

	public void setStart(Date startDate) {
		this.start = startDate;
	}

	public void setEnd(Date endDate) {
		this.end = endDate;
	}

	public void setPayee(Payee payee) {
		this.payee = payee;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}

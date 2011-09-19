package models;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPA;
import play.db.jpa.Model;


@Entity(name="transactions")
public class Transaction extends Model {

	@MaxSize(250)
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	
	@Required
	private BigDecimal amount;
	
	@Transient
	private BigDecimal balance;
	
	@OneToOne
	private Account account;
	
	@OneToOne(optional=true)
	private Payee payee;
	
	@OneToOne(optional=true)
	private Category category;
	
	private String payment;
	
	public Transaction() {}
	
	public Transaction(String description, String amount) {
		this.description = description;
		this.amount = new BigDecimal(amount);
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(Date date) {
		this.transactionDate = date;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Payee getPayee() {
		return payee;
	}
	
	public void setPayee(Payee payee) {
		this.payee = payee;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public static List<Transaction> filterByAccount(Account account) {
		return filter(account, null, null, null, null);
	}
	
	public static List<Transaction> filterByDateInterval(Account account, Date start, Date end) {
		return filter(account, start, end, null, null);
	}
	
	public static List<Transaction> filterByPayee(Account account, Payee payee) {
		return filter(account, null, null, payee, null);
	}
	
	public static List<Transaction> filterByCategory(Account account, Category category) {
		return filter(account, null, null, null, category);
	}
	
	public static List<Transaction> filter(Account account, Date start, Date end, Payee payee, Category category) {
		
		if (account == null) {
			return null;
		}
		
		StringBuffer sql = new StringBuffer("SELECT t FROM transactions t WHERE account_id='" + account.getId() + "' ");

		if (isNotNull(start)) {
			sql.append("AND transactionDate >= '" + formatDate(start) + "' ");
		}
		
		if (isNotNull(end)) {
			sql.append("AND transactionDate <= '" + formatDate(end) + "' ");
		}
		
		if (isNotNull(payee)) {
			sql.append("AND payee_id = '" + payee.getId() + "' ");
		}
		
		if (isNotNull(category)) {
			sql.append("AND category_id = '" + category.getId() + "' ");
		}
		
		Query query = JPA.em().createQuery(sql.toString());
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

	private static String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		return dateFormat.format(date);
	}
	
	private static boolean isNotNull(Object object) {
		return object != null;
	}
}

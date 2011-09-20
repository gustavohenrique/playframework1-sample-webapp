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
	
	@Required
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
		TransactionFilterOptions config = new TransactionFilterOptions();
		config.setAccount(account);
		return filter(config);
	}
	
	public static List<Transaction> filterByDateInterval(Account account, Date start, Date end) {
		TransactionFilterOptions config = new TransactionFilterOptions();
		config.setAccount(account);
		config.setStart(start);
		config.setEnd(end);
		return filter(config);
	}
	
	public static List<Transaction> filterByPayee(Account account, Payee payee) {
		TransactionFilterOptions config = new TransactionFilterOptions();
		config.setAccount(account);
		config.setPayee(payee);
		return filter(config);
	}
	
	public static List<Transaction> filterByCategory(Account account, Category category) {
		TransactionFilterOptions config = new TransactionFilterOptions();
		config.setAccount(account);
		config.setCategory(category);
		return filter(config);
	}
	
	public static List<Transaction> filter(TransactionFilterOptions config) {
		
		if (config == null || config.getAccount() == null) {
			return null;
		}
		
		StringBuffer sql = new StringBuffer("SELECT t FROM transactions t WHERE account_id='" + config.getAccount().getId() + "' ");

		if (isNotNull(config.getStart())) {
			sql.append("AND transactionDate >= '" + formatDate(config.getStart()) + "' ");
		}
		
		if (isNotNull(config.getEnd())) {
			sql.append("AND transactionDate <= '" + formatDate(config.getEnd()) + "' ");
		}
		
		if (isNotNull(config.getPayee())) {
			sql.append("AND payee_id = '" + config.getPayee().getId() + "' ");
		}
		
		if (isNotNull(config.getCategory())) {
			sql.append("AND category_id = '" + config.getCategory().getId() + "' ");
		}
		
		sql.append("ORDER BY transactionDate, balance");
		
		Query query = JPA.em().createQuery(sql.toString());
		query.setFirstResult(config.getStartPage());
		query.setMaxResults(config.getLimitPage());

		return query.getResultList(); 
	}

	private static String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		return dateFormat.format(date);
	}
	
	private static boolean isNotNull(Object object) {
		return object != null;
	}
}

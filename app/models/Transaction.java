package models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;


@Entity
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
}

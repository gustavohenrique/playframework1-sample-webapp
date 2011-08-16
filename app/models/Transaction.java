package models;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;
import play.db.jpa.Transactional;

@Entity
public class Transaction extends Model {

	@MaxSize(250)
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Calendar date;
	
	@Required
	private BigDecimal amount;
	
	@OneToOne
	private Account account;
	
	@OneToOne(optional=true)
	private Payee payee;
	
	@OneToOne(optional=true)
	private Category category;
	
	@OneToOne(optional=true)
	private Payment payment;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Calendar getDate() {
		return date;
	}
	
	public void setDate(Calendar date) {
		this.date = date;
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
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}

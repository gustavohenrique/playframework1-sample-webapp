package models;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPA;
import play.db.jpa.Model;
import pojo.TransactionFilterOptions;


@Entity(name="transactions")
public class Transaction extends Model {
	
	@Required
	@ManyToOne
	public User user;

	@MaxSize(250)
	public String description;
	
	@Temporal(TemporalType.DATE)
	public Date transactionDate;
	
	@Required
	public BigDecimal amount;
	
	@Required
	@OneToOne
	public Account account;
	
	@OneToOne(optional=true)
	public Payee payee;
	
	@OneToOne(optional=true)
	public Category category;
	
	public String payment;
	
	public Transaction() {}
	
	public Transaction(String description, String amount) {
		this.description = description;
		this.amount = new BigDecimal(amount);
	}
	
	public static List<Transaction> filter(TransactionFilterOptions config) {
		
		if (config == null || config.getUserId() == 0 || config.getAccountId() == 0) {
			return null;
		}
		
		StringBuffer sql = new StringBuffer("SELECT t FROM transactions t WHERE user_id='" + config.getUserId()
				+ "' AND account_id='" + config.getAccountId() + "' ");

		if (isNotNull(config.getStart())) {
			sql.append("AND transactionDate >= '" + formatDate(config.getStart()) + "' ");
		}
		
		if (isNotNull(config.getEnd())) {
			sql.append("AND transactionDate <= '" + formatDate(config.getEnd()) + "' ");
		}
		
		if (isBiggerThanZero(config.getTransactionId())) {
			sql.append("AND id = '" + config.getTransactionId() + "' ");
		}
		
		if (isBiggerThanZero(config.getPayeeId())) {
			sql.append("AND payee_id = '" + config.getPayeeId() + "' ");
		}
		
		if (isBiggerThanZero(config.getCategoryId())) {
			sql.append("AND category_id = '" + config.getCategoryId() + "' ");
		}
		
		String orderBy = "transactionDate DESC, amount DESC";
		if (isNotNull(config.getOrderBy())) {
			orderBy = config.getOrderBy();
		}
		sql.append("ORDER BY " + orderBy);
		
		Query query = JPA.em().createQuery(sql.toString());
		query.setFirstResult(config.getStartPage());
		query.setMaxResults(config.getLimitPage());

		return query.getResultList(); 
	}

	public static String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		return dateFormat.format(date);
	}
	
	public static boolean isNotNull(Object object) {
		return object != null;
	}
	
	private static boolean isBiggerThanZero(Long value) {
		return value != null && value > 0;
	}
}

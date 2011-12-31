package models;

import java.util.Calendar;
import java.util.Date;

public class TransactionFilterOptions {

	private Long userId;
	private Long accountId;
	private Long transactionId;
	private Long payeeId;
	private Long categoryId;
	
	private Date start;
	private Date end;
	
	private int startPage = 0;
	private int limitPage = 31;
	
	private String orderBy;
	
	public Date getStart() {
		if (start == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(1984, Calendar.JANUARY, 1);
			return calendar.getTime();
		}
		return start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getEnd() {
		if (end == null) {
			return Calendar.getInstance().getTime();
		}
		return end;
	}
	
	public void setEnd(Date end) {
		this.end = end;
	}
	
	public int getStartPage() {
		return startPage;
	}
	
	public int getLimitPage() {
		return limitPage;
	}
	
	public void setPage(int startPage, int limitPage) {
		this.startPage = startPage;
		this.limitPage = limitPage;
	}
	
	public void setPagination(String startPage, String limitPage) {
		try {
			this.startPage = Integer.valueOf(startPage);
		    if (this.startPage < 0) this.startPage = 0;
		    this.limitPage = Integer.valueOf(limitPage);
		}
		catch (Exception e) {
			// use default values on attribute declaration
		}
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
}

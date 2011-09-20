package models;

import java.util.Calendar;
import java.util.Date;

public class TransactionFilterOptions {

	private Account account;
	private Payee payee;
	private Category category;
	
	private Date start;
	private Date end;
	
	private int startPage = 0;
	private int limitPage = 31;
	
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
		   this.limitPage = Integer.valueOf(limitPage);
		}
		catch (Exception e) {
			// use default values on attribute declaration
		}
	}
}

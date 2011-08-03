package models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;

import play.data.validation.CheckWith;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Account extends Model {

	private int key;
	
	@Required
	@MaxSize(50)
	private String name;
	
	@MaxSize(20)
	private String number;
	
	private BigDecimal initial;
	
	private String type;
	
	public int getKey() {
		return key;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public BigDecimal getInitial() {
		return initial;
	}
	
	public void setInitial(BigDecimal initial) {
		this.initial = initial;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}

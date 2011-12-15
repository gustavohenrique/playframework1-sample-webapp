package models;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;

import play.data.validation.CheckWith;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity(name="accounts")
public class Account extends Model {

	@Required
	@ManyToOne(cascade=CascadeType.ALL)
	public User user;
	
	public int key;
	
	@Required
	@MaxSize(50)
	public String name;
	
	@MaxSize(20)
	public String number;
	
	public BigDecimal initial;
	
	public String type;
	
}

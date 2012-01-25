package models;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

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
	
	@Type(type="yes_no")
	public boolean enabled;
	
	@Required
	@MaxSize(50)
	public String name;
	
	@MaxSize(20)
	public String number;
	
	public BigDecimal initial;
	
	public String type;
	
}

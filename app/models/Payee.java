package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity(name="payees")
public class Payee extends Model {

	@Required
	@ManyToOne(cascade=CascadeType.ALL)
	public User user;
	
	public int key;
	
	@MaxSize(100)
	public String name;
	
}

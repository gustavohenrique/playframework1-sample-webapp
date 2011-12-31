package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.JPABase;
import play.db.jpa.Model;

@Entity(name="categories")
public class Category extends Model {

	@Required
	@ManyToOne(fetch=FetchType.EAGER)
	public User user;
	
	public int key;
	
	public int parent;
	
	public String name;
	
	@Override
	public <T extends JPABase> T delete() {
		List<Transaction> transctions = Transaction.find("byCategory", this).fetch();
		for (Transaction transaction : transctions) {
			transaction.category = null;
			transaction.save();
		}
		return super.delete();
	}
	
}

package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPABase;
import play.db.jpa.Model;

@Entity(name="payees")
public class Payee extends Model {

	@Required
	@ManyToOne(fetch=FetchType.EAGER)
	public User user;
	
	public int key;
	
	@MaxSize(100)
	@Column(unique=true)
	public String name;
	
	@Override
	public <T extends JPABase> T delete() {
		List<Transaction> transctions = Transaction.find("byPayee", this).fetch();
		for (Transaction transaction : transctions) {
			transaction.payee = null;
			transaction.save();
		}
		return super.delete();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}

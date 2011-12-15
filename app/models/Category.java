package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity(name="categories")
public class Category extends Model {

	@Required
	@ManyToOne(cascade=CascadeType.ALL)
	public User user;
	
	public int key;
	
	public int parent;
	
	public String name;
	
}

package models;

import javax.persistence.Entity;

import play.data.validation.MaxSize;
import play.db.jpa.Model;

@Entity(name="payees")
public class Payee extends Model {

	private int key;
	
	@MaxSize(100)
	private String name;
	
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
}

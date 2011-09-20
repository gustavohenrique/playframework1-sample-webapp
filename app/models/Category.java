package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity(name="categories")
public class Category extends Model {

	private int key;
	
	private int parent;
	
	private String name;
	
	public int getKey() {
		return key;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	public int getParent() {
		return parent;
	}
	
	public void setParent(int parent) {
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

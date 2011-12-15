package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity(name="users")
public class User extends Model {

	@Required
	@Column(unique=true)
	public String username;
	
	@Required
    public String password;
    
    public String fullname;
    
    public boolean isAdmin;
    
}

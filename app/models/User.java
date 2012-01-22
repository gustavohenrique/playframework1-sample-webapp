package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity(name="users")
public class User extends Model {

	@Required
	@Column(unique=true)
	public String username;
	
	@Required
    public String password;
	
	@Transient
	public String password2;
    
    public String fullname;
    
    public boolean isAdmin;
    
    @Override
    public String toString() {
    	return this.username;
    }
    
}

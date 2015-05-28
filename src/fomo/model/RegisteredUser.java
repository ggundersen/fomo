package fomo.model;

import javax.mail.internet.AddressException;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RegisteredUser")
public class RegisteredUser extends User {
	
	public RegisteredUser() {
	}

	public RegisteredUser(String name, String email, String password) throws AddressException {
		super(name, email);
	}
}
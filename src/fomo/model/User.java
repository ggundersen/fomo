package fomo.model;

import javax.mail.internet.AddressException;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import fomo.core.Password;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "is_host")
@Table(name = "User")
public class User extends Person {

	@Column(name = "password")
	private String password;

	public User() {
	}

	public User(String name, String email, String plaintext_password) throws AddressException {
		super(name, email);
		this.password = Password.hash(plaintext_password);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
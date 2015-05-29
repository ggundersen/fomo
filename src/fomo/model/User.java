package fomo.model;

import javax.mail.internet.AddressException;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.mindrot.jbcrypt.BCrypt;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "is_host")
@Table(name = "User")
public class User extends Person {

	@Column(name = "password")
	private String password;

	public User() {
	}

	public User(String name, String email, String plaintextPassword) throws AddressException {
		super(name, email);
		this.password = hash(plaintextPassword);
	}
	
	private String hash(String plaintextPassword) {
		// BCrypt incorporates the salt to the end of the password. We don't
		// have to store it.
		return BCrypt.hashpw(plaintextPassword, BCrypt.gensalt());
	}
	
	private boolean isValidPassword(String candiate) {
		return BCrypt.checkpw(candiate, this.password);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
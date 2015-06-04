package fomo.model;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "name")
	private String name;

	public Person() {
	}

	public Person(String email) throws AddressException {
		InternetAddress internetAddress = new InternetAddress(email);
		internetAddress.validate();
		this.email = internetAddress.toString();
	}

	public Person(String email, String name) throws AddressException {
		InternetAddress internetAddress = new InternetAddress(email);
		internetAddress.validate();
		this.email = internetAddress.toString();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
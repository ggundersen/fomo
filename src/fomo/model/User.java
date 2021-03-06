package fomo.model;

import java.util.Set;

import javax.mail.internet.AddressException;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.mindrot.jbcrypt.BCrypt;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "user")
public class User extends Person {

	@Column(name = "password", nullable = false)
	private String password;

	@OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
	private Set<Event> hostedEvents;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "guests_to_events",
		joinColumns = { @JoinColumn(name = "user_id") },
		inverseJoinColumns = { @JoinColumn(name = "event_id") })
	private Set<Event> invitedEvents;

	public User() {
	}
	
	public User(String email) throws AddressException {
		super(email);
	}

	public User(String email, String plaintextPassword, String name) throws AddressException {
		super(email, name);
		this.password = hash(plaintextPassword);
	}
	
	private String hash(String plaintextPassword) {
		// BCrypt incorporates the salt to the end of the password. We don't
		// have to store it.
		return BCrypt.hashpw(plaintextPassword, BCrypt.gensalt());
	}
	
	public boolean isValidPassword(String candiate) {
		return BCrypt.checkpw(candiate, this.password);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Event> getHostedEvents() {
		return hostedEvents;
	}

	public void setHostedEvents(Set<Event> hostedEvents) {
		this.hostedEvents = hostedEvents;
	}

	public Set<Event> getInvitedEvents() {
		return invitedEvents;
	}

	public void setInvitedEvents(Set<Event> invitedEvents) {
		this.invitedEvents = invitedEvents;
	}
}
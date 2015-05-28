package fomo.model;

import javax.mail.internet.AddressException;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Guest")
public class Guest extends User {

	@OneToOne(mappedBy = "guest", cascade = CascadeType.ALL)
	private Invite invite;

	public Guest() {
	}

	public Guest(String firstName, String name) throws AddressException {
		super(firstName, name);
	}

	public Invite getEvent() {
		return invite;
	}

	public void setEvent(Invite invite) {
		this.invite = invite;
	}
}
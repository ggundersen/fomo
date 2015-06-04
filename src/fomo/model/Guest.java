package fomo.model;

import javax.mail.internet.AddressException;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Guest")
public class Guest extends Person {

	@OneToOne(mappedBy = "guest", cascade = CascadeType.ALL)
	private Invite invite;

	public Guest() {
	}

	public Guest(String email) throws AddressException {
		super(email);
	}

	public Invite getEvent() {
		return invite;
	}

	public void setEvent(Invite invite) {
		this.invite = invite;
	}
}
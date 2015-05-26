package fomo.model;

import javax.mail.internet.AddressException;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Guest")
public class Guest extends User {

	@OneToOne(mappedBy = "guest")
	private Invite invite;

	public Guest(String firstName, String lastName, String email)
			throws AddressException {
		super(firstName, lastName, email);
	}

	public Invite getEvent() {
		return invite;
	}

	public void setEvent(Invite invite) {
		this.invite = invite;
	}
}
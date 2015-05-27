package fomo.model;

import java.util.Set;

import javax.mail.internet.AddressException;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Host")
public class Host extends User {

	@OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
	private Set<Event> events;

	public Host() {
	}

	public Host(String firstName, String lastName, String email)
			throws AddressException {
		super(firstName, lastName, email);
	}

	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvent(Set<Event> events) {
		this.events = events;
	}
}
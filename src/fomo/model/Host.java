package fomo.model;

import java.util.Set;

import javax.mail.internet.AddressException;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "true")
public class Host extends User {

	@OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
	private Set<Event> events;

	public Host() {
	}
	
	public Host(String email, String password, String name) throws AddressException {
		super(email, password, name);
	}

	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvent(Set<Event> events) {
		this.events = events;
	}
}
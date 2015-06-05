package fomo.model;

import java.util.Set;

import javax.mail.internet.AddressException;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import fomo.util.DAO;

@Entity
@DiscriminatorValue(value = "true")
public class Host extends User {

	@OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
	private Set<Event> events;

	public Host() {
	}
	
	public Host(String email, String password) throws AddressException {
		super(email, password, DAO.getUsernameByEmail(email));
	}

	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvent(Set<Event> events) {
		this.events = events;
	}
}
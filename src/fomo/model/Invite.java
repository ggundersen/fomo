package fomo.model;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Invite")
public class Invite {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "expiration_date")
	private Timestamp expirationDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "guest_fk")
	private Guest guest;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "event_fk")
	private Event event;

	public Invite() {
	}

	public Invite(Event event, Guest guest) {
		this.uuid = UUID.randomUUID().toString();
		this.expirationDate = null;
		this.event = event;
		this.guest = guest;
	}

	public long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Timestamp getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
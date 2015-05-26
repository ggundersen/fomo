package fomo.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Invite")
public class Invite {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "invitee")
	private String inviteeName;

	@Column(name = "expired")
	private boolean expired;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_fk")
	private Event event;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Guest guest;

	public Invite(Event event, Guest guest) {
		this.uuid = UUID.randomUUID().toString();
		this.expired = false;
		this.event = event;
		this.guest = guest;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String tempId) {
		this.uuid = tempId;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event dbEvent) {
		this.event = dbEvent;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
}
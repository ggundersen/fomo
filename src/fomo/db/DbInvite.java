package fomo.db;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Invites")
public class DbInvite {

	@Id
	@GeneratedValue
	@Column(name = "event_id")
	private int invite_id;

	@Column(name = "name")
	private String name;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "invitee")
	private String inviteeName;

	@Column(name = "expired")
	private boolean expired;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="event_fk")
	private DbEvent event;

	public DbInvite() {
	}

	public DbInvite(String name, DbEvent event) {
		this.name = name;
		this.uuid = UUID.randomUUID().toString();
		this.expired = false;
		this.event = event;
	}

	public int getId() {
		return this.invite_id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String tempId) {
		this.uuid = tempId;
	}

	public String getInviteeName() {
		return this.inviteeName;
	}

	public void setInviteeName(String inviteeName) {
		this.inviteeName = inviteeName;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public DbEvent getEvent() {
		return this.event;
	}

	public void setEvent(DbEvent dbEvent) {
		this.event = dbEvent;
	}
}
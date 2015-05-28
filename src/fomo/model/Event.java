package fomo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Event")
public class Event {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "host_fk")
	private Host host;

	@Column(name = "datetime")
	private Date datetime;

	@Column(name = "location")
	private String location;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	private Set<Invite> invites;

	public Event() {
	}

	public Event(Host host, String name, Date datetime, String location,
			String description) {

		this.host = host;
		this.name = name;
		this.datetime = datetime;
		this.location = location;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Host getHost() {
		return host;
	}

	public void setHostName(Host host) {
		this.host = host;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Invite> getInvites() {
		return invites;
	}
}

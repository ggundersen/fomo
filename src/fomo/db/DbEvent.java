package fomo.db;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Events")
public class DbEvent {

	@Id
	@GeneratedValue
	@Column(name = "event_id")
	private int event_id;

	@Column(name = "name")
	private String name;

	@Column(name = "host_name")
	private String hostName;

	@Column(name = "datetime")
	private Date datetime;

	@Column(name = "location")
	private String location;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "event")
	private Set<DbInvite> invites;

	public DbEvent() {
	}

	public DbEvent(String name, String hostName, Date datetime,
			String location, String description) {
		this.name = name;
		this.hostName = hostName;
		this.datetime = datetime;
		this.location = location;
		this.description = description;
	}

	public int getId() {
		return this.event_id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<DbInvite> getInvites() {
		return this.invites;
	}
}

package fomo.dao;

import java.util.Date;
import java.util.Set;

import org.hibernate.Session;

import fomo.db.DbEvent;
import fomo.db.DbInvite;

public class DaoEvent {

	public static DbEvent create(Session dbSession, String name,
			String hostName, Date datetime, String location, String description) {

		DbEvent dbEvent = new DbEvent(name, hostName, datetime, location,
				description);
		dbSession.save(dbEvent);
		dbSession.flush();
		return dbEvent;
	}
}
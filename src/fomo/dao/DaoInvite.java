package fomo.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fomo.db.DbEvent;
import fomo.db.DbInvite;

public class DaoInvite {

	public static DbInvite getByTempId(Session dbSession, String tempId) {
		Criteria criteria = dbSession.createCriteria(DbInvite.class).add(
				Restrictions.eq("tempId", tempId).ignoreCase());
		DbInvite dbInvite = (DbInvite) criteria.uniqueResult();
		return dbInvite;
	}

	public static DbInvite create(Session dbSession, DbEvent dbEvent,
			String name) {
		DbInvite dbInvite = new DbInvite(name, dbEvent);
		dbSession.save(dbInvite);
		dbSession.flush();
		return dbInvite;
	}
}
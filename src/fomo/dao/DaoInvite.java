package fomo.dao;

import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fomo.db.DbInvite;

public class DaoInvite {

	public static DbInvite getByTempId(Session dbSession, UUID tempId) {
		Criteria criteria = dbSession.createCriteria(DbInvite.class).add(
				Restrictions.eq("tempId", tempId).ignoreCase());
		DbInvite dbInvite = (DbInvite) criteria.uniqueResult();
		return dbInvite;
	}

	public static String create(Session dbSession, String name) {
		DbInvite dbInvite = new DbInvite(name);
		dbSession.save(dbInvite);
		dbSession.flush();
		return dbInvite.getUuid();
	}
}
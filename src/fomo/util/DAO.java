package fomo.util;

import org.hibernate.Criteria;
import org.hibernate.connection.HibernateUtil;
import org.hibernate.criterion.Restrictions;

import fomo.model.User;

public class DAO {

	public static String getUsernameByEmail(String email) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email).ignoreCase());
		User user = (User) criteria.uniqueResult();
		return user.getName();
	}
	
	public static User getUserByEmail(String email) {
		Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email).ignoreCase());
		return (User) criteria.uniqueResult();	
	}
}

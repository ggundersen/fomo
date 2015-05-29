package org.hibernate.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		try {
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void beginTransaction() {
		getCurrentSession().beginTransaction();
	}

	public static void saveOrUpdate(Object object) {
		getCurrentSession().saveOrUpdate(object);
	}

	public static void commitTransaction() {
		getCurrentSession().getTransaction().commit();
	}

	public static void rollbackTransaction() {
		getCurrentSession().getTransaction().rollback();
	}

	public static void closeSession() {
		getCurrentSession().close();
	}

	public static void shutdown() {
		sessionFactory.close();
	}
}

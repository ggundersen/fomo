package fomo.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.connection.HibernateUtil;

@WebListener
public class ContextManager implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
	}       

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		HibernateUtil.shutdown();
	}
}
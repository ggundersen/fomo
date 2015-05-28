package fomo.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.connection.HibernateUtil;
import org.hibernate.criterion.Restrictions;

import fomo.model.Event;
import fomo.model.Host;
import fomo.model.Invite;
import fomo.util.Constant;

@WebServlet("/invite/*")
public class InviteAPI extends HttpServlet {

	private static final long serialVersionUID = 1563652678138596437L;

	private static long DURATION_SECS = 60;
	private static long NANOSECS = 1000000000;
	private static long MAX_TIME_NANOSECS = DURATION_SECS * NANOSECS;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getPathInfo();
		if (path == null) {
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "404.html").forward(req, resp);
			return;
		}
		path = path.substring(1);

		Session dbSession = null;
		Transaction dbTransaction = null;
		Invite invite = null;
		try {
			dbSession = HibernateUtil.getSessionFactory().openSession();
			dbTransaction = dbSession.beginTransaction();
			Criteria criteria = dbSession.createCriteria(Invite.class).add(Restrictions.eq("uuid", path).ignoreCase());
			invite = (Invite) criteria.uniqueResult();

			System.out.println(invite);
			if (invite == null) {
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "404.html").forward(req, resp);
				return;
			}

			Event event = invite.getEvent();
			long eventId = event.getId();
			String name = event.getName();
			Host host = event.getHost();
			String location = event.getLocation();
			String hostName = host.getName();
			String description = event.getDescription();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			String datetime = df.format(event.getDatetime());

			Long expirationTime = invite.getExpirationTime();
			long now = System.nanoTime();
			if (expirationTime == null) {
				long newExpirationTime = System.nanoTime() + MAX_TIME_NANOSECS;
				invite.setExpirationTime(newExpirationTime);
				dbSession.merge(invite);
				req.getSession().setAttribute("name", name);
				req.getSession().setAttribute("host", hostName);
				req.getSession().setAttribute("time", datetime);
				req.getSession().setAttribute("location", location);
				req.getSession().setAttribute("description", description);
				req.getSession().setAttribute("timeLeft", DURATION_SECS);
				req.getSession().setAttribute("eventId", eventId);
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "invite.jsp").forward(req, resp);
			} else if (now - expirationTime < 0) {
				req.getSession().setAttribute("name", name);
				req.getSession().setAttribute("host", hostName);
				req.getSession().setAttribute("time", datetime);
				req.getSession().setAttribute("location", location);
				req.getSession().setAttribute("description", description);
				req.getSession().setAttribute("timeLeft", (expirationTime - now) / NANOSECS);
				req.getSession().setAttribute("eventId", path);
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "invite.jsp").forward(req, resp);
			} else if (now - expirationTime > 0) {
				req.getRequestDispatcher(Constant.TEMPLATE_DIR + "expired.html").forward(req, resp);
			}

			dbTransaction.commit();
		} catch (HibernateException he) {
			// TODO: Handle this.
		} finally {
			if (dbSession != null) {
				dbSession.close();
			}
		}
	}
}

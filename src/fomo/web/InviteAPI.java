package fomo.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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

import fomo.model.Invite;

@WebServlet("/invite/*")
public class InviteAPI extends HttpServlet {

	private static final long serialVersionUID = 1563652678138596437L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String path = req.getPathInfo();
		if (path == null) {
			req.getRequestDispatcher("/html/404.html").forward(req, resp);
			return;
		}
		path = path.substring(1);

		Session dbSession = null;
		Transaction dbTransaction = null;
		Invite invite = null;
		try {
			dbSession = HibernateUtil.getSessionFactory().openSession();
			dbTransaction = dbSession.beginTransaction();
			Criteria criteria = dbSession.createCriteria(Invite.class).add(
					Restrictions.eq("uuid", path).ignoreCase());
			invite = (Invite) criteria.uniqueResult();
			
			System.out.println(invite);
			if (invite == null) {
				req.getRequestDispatcher("/html/404.html").forward(req, resp);
				return;
			}
			
			Timestamp expirationDate = invite.getExpirationDate();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			if (expirationDate == null) {
				invite.setExpirationDate(new Timestamp(System.currentTimeMillis() + 60 * 1000));
				expirationDate = invite.getExpirationDate();
				req.getSession().setAttribute("timeLeft", diff(now, expirationDate));
				req.getRequestDispatcher("/html/invite.jsp").forward(req, resp);
				return;
			} else if (expirationDate.after(now)) {
				req.getRequestDispatcher("/html/expired.html").forward(req,
						resp);
				return;
			} else if (expirationDate.before(now)) {
				req.getSession().setAttribute("timeLeft", diff(now, expirationDate));
				req.getRequestDispatcher("/html/invite.jsp")
						.forward(req, resp);
				return;
			}

			dbTransaction.commit();
		} catch (HibernateException he) {

		} finally {
			if (dbSession != null) {
				dbSession.close();
			}
		}
	}

	public Timestamp diff(java.util.Date t1, java.util.Date t2) {
		// Make sure the result is always > 0
		if (t1.compareTo(t2) < 0) {
			java.util.Date tmp = t1;
			t1 = t2;
			t2 = tmp;
		}

		// Timestamps mix milli and nanoseconds in the API, so we have to
		// separate the two
		long diffSeconds = (t1.getTime() / 1000) - (t2.getTime() / 1000);
		// For normals dates, we have millisecond precision
		int nano1 = ((int) t1.getTime() % 1000) * 1000000;
		// If the parameter is a Timestamp, we have additional precision in
		// nanoseconds
		if (t1 instanceof Timestamp)
			nano1 = ((Timestamp) t1).getNanos();
		int nano2 = ((int) t2.getTime() % 1000) * 1000000;
		if (t2 instanceof Timestamp)
			nano2 = ((Timestamp) t2).getNanos();

		int diffNanos = nano1 - nano2;
		if (diffNanos < 0) {
			// Borrow one second
			diffSeconds--;
			diffNanos += 1000000000;
		}

		// mix nanos and millis again
		Timestamp result = new Timestamp((diffSeconds * 1000)
				+ (diffNanos / 1000000));
		// setNanos() with a value of in the millisecond range doesn't affect
		// the value of the time field
		// while milliseconds in the time field will modify nanos! Damn, this
		// API is a *mess*
		result.setNanos(diffNanos);
		return result;
	}
}

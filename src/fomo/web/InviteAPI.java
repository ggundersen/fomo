package fomo.web;

import java.io.IOException;

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

import fomo.core.TransientInvite;
import fomo.model.Invite;

@WebServlet("/invite/*")
public class InviteAPI extends HttpServlet {

	private static final long serialVersionUID = 1563652678138596437L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String path = req.getPathInfo();
		if (path != null) {
			path = path.substring(1);
		}
		
		Session dbSession = null;
		Transaction dbTransaction = null;
		Invite invite = null;
		String eventName = null;
		try {
			dbSession = HibernateUtil.getSessionFactory().openSession();
			dbTransaction = dbSession.beginTransaction();
			Criteria criteria = dbSession.createCriteria(Invite.class).add(
					Restrictions.eq("uuid", path).ignoreCase());
			invite = (Invite) criteria.uniqueResult();
			eventName = invite.getEvent().getName();
			dbTransaction.commit();
		} catch (HibernateException he) {
			dbTransaction.rollback();
		} finally {
			if (dbSession != null) {
				dbSession.close();
			}
		}
		
		System.out.println(eventName);

		new TransientInvite();
		req.getRequestDispatcher("/html/invite.jsp").forward(req, resp);
	}
}

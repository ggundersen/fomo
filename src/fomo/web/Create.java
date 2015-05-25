package fomo.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.connection.HibernateUtil;

import fomo.dao.DaoEvent;
import fomo.dao.DaoInvite;
import fomo.db.DbEvent;
import fomo.db.DbInvite;

@WebServlet("/create")
public class Create extends HttpServlet {

	private static final long serialVersionUID = -7444063209623350028L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String newTempId = null;
		String name = "summertime cookout";
		String hostName = "biggie";
		Date datetime = new Date();
		String location = "brooklyn";
		String description = "bullshit and party";
		DbEvent dbEvent = null;
		Session dbSession = null;
		Transaction dbTransaction = null;
		try {
			dbSession = HibernateUtil.getSessionFactory().openSession();
			dbTransaction = dbSession.beginTransaction();

			// Create event
			dbEvent = DaoEvent.create(dbSession, name, hostName, datetime,
					location, description);

			// Create a set of invites
			Set<DbInvite> invites = new HashSet<DbInvite>();
			invites.add(DaoInvite.create(dbSession, dbEvent, "2pac"));

			dbTransaction.commit();
		} catch (HibernateException he) {
			dbTransaction.rollback();
		} finally {
			if (dbSession != null) {
				dbSession.close();
			}
		}
		System.out.println(dbEvent);
		req.getSession().setAttribute("url",
				"http://localhost:8080/fomo/invite/" + newTempId);
		RequestDispatcher view = req.getRequestDispatcher("html/create.jsp");
		view.forward(req, resp);
		// Email.send();
	}
}

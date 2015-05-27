package fomo.web;

import java.io.IOException;
import java.util.Date;

import javax.mail.internet.AddressException;
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

import fomo.model.Event;
import fomo.model.Guest;
import fomo.model.Host;
import fomo.model.Invite;

@WebServlet("/create")
public class CreateAPI extends HttpServlet {

	private static final long serialVersionUID = -7444063209623350028L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Event event = null;
		Host host = null;
		Session dbSession = null;
		Transaction dbTransaction = null;
		Invite invite = null;
		try {
			dbSession = HibernateUtil.getSessionFactory().openSession();
			dbTransaction = dbSession.beginTransaction();
			host = new Host("Christopher", "Wallace", "biggie@gmail.com");
			event = new Event(host, "Summertime Cookout", new Date(),
					"Bed-Stuy", "Bullshit and party");
			Guest guest = new Guest("Shawn", "Carter", "jayz@gmail.com");
			invite = new Invite(event, guest);
			dbSession.save(invite);
			dbSession.flush();
			dbTransaction.commit();
		} catch (HibernateException he) {
			dbTransaction.rollback();
		} catch (AddressException e) {
			// Invalid user email address.
			// This should be handled by some new use validation.
		} finally {
			if (dbSession != null) {
				dbSession.close();
			}
		}
		String uuid = invite.getUuid();
		req.getSession().setAttribute("url",
				"http://localhost:8080/fomo/invite/" + uuid);
		RequestDispatcher view = req.getRequestDispatcher("html/create.jsp");
		view.forward(req, resp);
		// Email.send();
	}
}
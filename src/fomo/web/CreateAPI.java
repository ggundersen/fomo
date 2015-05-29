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
import org.hibernate.connection.HibernateUtil;

import fomo.model.Event;
import fomo.model.Guest;
import fomo.model.Host;
import fomo.model.Invite;
import fomo.util.Constant;

@WebServlet("/create")
public class CreateAPI extends HttpServlet {

	private static final long serialVersionUID = -7444063209623350028L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Event event = null;
		Host host = null;
		Invite invite = null;
		try {
			HibernateUtil.beginTransaction();
			host = new Host("Christopher Wallace", "biggie@gmail.com");
			event = new Event(host, "Summertime Cookout", new Date(), "Bed-Stuy", "Bullshit and party");
			Guest guest = new Guest("Shawn Carter", "jayz@gmail.com");
			invite = new Invite(event, guest);
			HibernateUtil.saveOrUpdate(invite);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		} catch (AddressException e) {
			// TODO.
		} finally {
			HibernateUtil.closeSession();
		}

		req.getSession().setAttribute("url", "http://localhost:8080/fomo/invite/" + invite.getUuid());
		RequestDispatcher view = req.getRequestDispatcher(Constant.TEMPLATE_DIR + "create.jsp");
		view.forward(req, resp);
	}
}

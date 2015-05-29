package fomo.web;

import javax.mail.internet.AddressException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.connection.HibernateUtil;
import org.hibernate.criterion.Restrictions;

import fomo.model.User;

@WebServlet(urlPatterns = { "/register", "/login" })
public class AccountAPI extends HttpServlet {

	private static final long serialVersionUID = -196601566041600671L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getServletPath().equals("/login")) {
			login(req, resp);
		} else if (req.getServletPath().equals("/register")) {
			try {
				register(req, resp);
			} catch (AddressException e) {
				// Invalid email address.
			}
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) {

	}

	private void register(HttpServletRequest req, HttpServletResponse resp) throws AddressException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		try {
			HibernateUtil.beginTransaction();
			Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email).ignoreCase());
			User user = (User) criteria.uniqueResult();
			if (user != null) {
				// User exists.
			} else {
				User newUser = new User(name, email, password);
				HibernateUtil.saveOrUpdate(newUser);
				req.getSession().setAttribute("user", newUser);
			}
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			// TODO.
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
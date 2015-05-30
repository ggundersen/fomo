package fomo.web;

import java.io.IOException;

import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.connection.HibernateUtil;
import org.hibernate.criterion.Restrictions;

import fomo.model.User;
import fomo.util.Constant;

@WebServlet(urlPatterns = { "/register", "/login" })
public class AccountAPI extends HttpServlet {

	private static final long serialVersionUID = -196601566041600671L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/login")) {
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "login.jsp").forward(req, resp);
		} else if (path.equals("/register")) {
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "register.jsp").forward(req, resp);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String path = req.getPathInfo();
		if (path.equals("/login")) {
			login(req, resp);
		} else if (path.equals("/register")) {
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
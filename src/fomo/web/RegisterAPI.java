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

@WebServlet(urlPatterns = { "/register" })
public class RegisterAPI extends HttpServlet {

	private static final long serialVersionUID = 1162465387654557280L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Constant.TEMPLATE_DIR + "register.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String username = req.getParameter("username");
		User user = null;
		boolean userAlreadyExists = false;

		try {
			HibernateUtil.beginTransaction();
			Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email).ignoreCase());
			user = (User) criteria.uniqueResult();
			if (user != null) {
				userAlreadyExists = true;
			} else {
				user = new User(email, password, username);
				HibernateUtil.saveOrUpdate(user);
			}
			HibernateUtil.commitTransaction();
		} catch (AddressException | HibernateException e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}

		if (user != null && !userAlreadyExists) {
			req.setAttribute("email", user.getEmail());
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "user_success.jsp").forward(req, resp);
		}
	}
}

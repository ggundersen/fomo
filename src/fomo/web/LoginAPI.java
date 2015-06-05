package fomo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.connection.HibernateUtil;

import fomo.model.User;
import fomo.util.Constant;
import fomo.util.DAO;

@WebServlet(urlPatterns = { "/login" })
public class LoginAPI extends HttpServlet {

	private static final long serialVersionUID = -196601566041600671L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Constant.TEMPLATE_DIR + "login.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String candidatePassword = req.getParameter("password");
		User user = null;
		
		try {
			HibernateUtil.beginTransaction();
			user = DAO.getUserByEmail(email);
			HibernateUtil.commitTransaction();
		} catch (HibernateException he) {
			HibernateUtil.rollbackTransaction();
		} finally {
			HibernateUtil.closeSession();
		}
	
		if (user != null && user.isValidPassword(candidatePassword)) {
			Cookie cookie = new Cookie("fomo", email);
			cookie.setMaxAge(60 * 60);
	        resp.addCookie(cookie);
			req.getRequestDispatcher(Constant.TEMPLATE_DIR + "index.jsp").forward(req, resp);
		} else {
			System.out.println("Failed to login");
		}
		
	}
}
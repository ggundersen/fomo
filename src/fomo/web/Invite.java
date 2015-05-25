package fomo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.connection.HibernateUtil;

@WebServlet("/invite/*")
public class Invite extends HttpServlet {

	private static final long serialVersionUID = 1563652678138596437L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String path = req.getPathInfo();
		/*
		 * if (path != null) { path = path.substring(1); }
		 */
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			// call a DAO method?
			// getInviteByUrl();
			// This is too generic?x
		} catch (Exception he) {

		} finally {
			session.close();
		}

		req.getRequestDispatcher("/html/invite.html").forward(req, resp);
	}
}

package fomo.web;

import java.io.IOException;

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

import fomo.dao.DaoInvite;

@WebServlet("/create")
public class Create extends HttpServlet {

	private static final long serialVersionUID = -7444063209623350028L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String newTempId = null;
		Session dbSession = null;
		Transaction dbTransaction = null;
		try {
			dbSession = HibernateUtil.getSessionFactory().openSession();
			dbTransaction = dbSession.beginTransaction();
			newTempId = DaoInvite.create(dbSession, "foo");
		} catch (HibernateException he) {
			dbTransaction.rollback();
		} finally {
			if (dbSession != null) {
				dbSession.close();
			}
		}
		req.getSession().setAttribute("url",
				"http://localhost:8080/fomo/invite/" + newTempId);
		RequestDispatcher view = req.getRequestDispatcher("html/create.jsp");
		view.forward(req, resp);
		// Email.send();
	}
}

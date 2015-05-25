package fomo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.connection.HibernateUtil;

import fomo.core.TransientInvite;
import fomo.dao.DaoInvite;
import fomo.db.DbInvite;

@WebServlet("/invite/*")
public class Invite extends HttpServlet {

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
		DbInvite dbInvite = null;
		try {
			dbSession = HibernateUtil.getSessionFactory().openSession();
			dbTransaction = dbSession.beginTransaction();
			dbInvite = DaoInvite.getByTempId(dbSession, path);
		} catch (HibernateException he) {
			dbTransaction.rollback();
		} finally {
			if (dbSession != null) {
				dbSession.close();
			}
		}
		
		System.out.println(dbInvite.getEvent().getName());

		new TransientInvite();
		req.getRequestDispatcher("/html/invite.jsp").forward(req, resp);
	}
}

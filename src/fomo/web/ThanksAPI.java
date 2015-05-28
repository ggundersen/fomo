package fomo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fomo.util.Constant;

@WebServlet("/thanks/*")
public class ThanksAPI extends HttpServlet {

	private static final long serialVersionUID = -956766345178079716L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("thanks.");
		String path = req.getPathInfo();
		if (path != null) {
			path = path.substring(1);
		}
		req.getRequestDispatcher(Constant.TEMPLATE_DIR + "thanks.jsp").forward(req, resp);
	}
}
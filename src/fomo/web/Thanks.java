package fomo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/thanks/*")
public class Thanks extends HttpServlet {

	private static final long serialVersionUID = -956766345178079716L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String path = req.getPathInfo();
		if (path != null) {
			path = path.substring(1);
		}
		req.getRequestDispatcher("/html/thanks.html").forward(req, resp);
	}
}
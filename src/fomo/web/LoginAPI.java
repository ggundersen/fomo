package fomo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fomo.util.Constant;

@WebServlet(urlPatterns = { "/login" })
public class LoginAPI extends HttpServlet {

	private static final long serialVersionUID = -196601566041600671L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Constant.TEMPLATE_DIR + "login.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		//Host host = new Host("Christopher Wallace", "biggie@gmail.com", "password");
		String username = req.getParameter("username");
		System.out.println(username);
	}
}
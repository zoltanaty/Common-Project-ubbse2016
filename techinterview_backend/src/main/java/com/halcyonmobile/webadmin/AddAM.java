package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.halcyonmobile.rest.OwnersService;

public class AddAM extends HttpServlet {
	private static final long serialVersionUID = 803689272257463915L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		String privilege = req.getParameter("radio");

		if (username.equals("") && password.equals("")) {
			RequestDispatcher view = req.getRequestDispatcher("manage.jsp");

			view.forward(req, res);
		} else {

			int priv = 1;

			if (privilege.equals("Manager")) {
				priv = 2;
			}

			OwnersService os = new OwnersService();
			os.addOwner(username, password, priv);

			RequestDispatcher view = req.getRequestDispatcher("manage.jsp");

			view.forward(req, res);
		}
	}
}
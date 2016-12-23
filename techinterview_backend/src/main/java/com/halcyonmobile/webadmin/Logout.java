package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends HttpServlet{
	private static final long serialVersionUID = -7384328555777618397L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.getSession().invalidate();
		System.out.println("Session destroyed");
		
		res.sendRedirect("index.jsp");
	}
}

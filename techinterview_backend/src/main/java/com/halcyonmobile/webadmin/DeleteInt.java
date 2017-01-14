package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.halcyonmobile.rest.UserService;

public class DeleteInt extends HttpServlet{
	private static final long serialVersionUID = 8241561223416285290L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.isNew()) {
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, res);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("manage.jsp");
			
			view.forward(req, res);
		}
		
		String name = req.getParameter("IntList");
		
		UserService us = new UserService();
		us.deleteUser(name);
		
		RequestDispatcher view = req.getRequestDispatcher("manage.jsp");

		view.forward(req, res);
	}
}

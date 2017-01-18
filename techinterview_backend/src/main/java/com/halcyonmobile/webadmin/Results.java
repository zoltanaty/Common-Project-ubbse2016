package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Results extends HttpServlet{
	private static final long serialVersionUID = 11L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
HttpSession session = req.getSession();
		
		if(session.isNew()) {
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, res);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("results.jsp");
			
			view.forward(req, res);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res){		
		System.out.println("in post");
	}
}

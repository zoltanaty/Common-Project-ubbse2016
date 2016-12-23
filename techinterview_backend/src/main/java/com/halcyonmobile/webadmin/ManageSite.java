package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ManageSite extends HttpServlet{
	private static final long serialVersionUID = 14L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		
		if(session.isNew()) {
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, res);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("manage.jsp");
			
			view.forward(req, res);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res){		
		System.out.println("in post");
	}
}

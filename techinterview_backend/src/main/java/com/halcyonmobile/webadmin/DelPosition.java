package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.halcyonmobile.model.Position;
import com.halcyonmobile.rest.PositionService;

public class DelPosition extends HttpServlet{
	private static final long serialVersionUID = -4654145885240152936L;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.isNew()) {
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, res);
		
			return;
		}
		
		int pos = Integer.parseInt(req.getParameter("posname"));
		
		PositionService ps = new PositionService();
		Position position = ps.findById(pos);
		ps.deletePosition(position);
		
		RequestDispatcher view = req.getRequestDispatcher("manage.jsp");

		view.forward(req, res);
	}
}

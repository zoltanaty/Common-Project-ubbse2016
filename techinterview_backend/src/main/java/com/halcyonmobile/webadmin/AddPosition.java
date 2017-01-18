package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.halcyonmobile.rest.PositionService;

public class AddPosition extends HttpServlet{
	private static final long serialVersionUID = 2199347976163280624L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.isNew()) {
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, res);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("manage.jsp");
			
			view.forward(req, res);
		}
		
		String pos = req.getParameter("posname");
		int nrQue = Integer.parseInt(req.getParameter("nrQue"));

		PositionService ps = new PositionService();
		ps.addPosition(pos, nrQue);
		
		RequestDispatcher view = req.getRequestDispatcher("manage.jsp");

		view.forward(req, res);
	}
}

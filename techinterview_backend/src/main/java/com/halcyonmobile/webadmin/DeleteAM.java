package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.halcyonmobile.model.Owners;
import com.halcyonmobile.rest.OwnersService;

public class DeleteAM extends HttpServlet {
	private static final long serialVersionUID = 5958447182579200053L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.isNew()) {
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, res);
		
			return;
		}
		
		String username = req.getParameter("AMList");
		
		OwnersService os = new OwnersService();
		Owners owner = os.findByUsername(username);
		os.deleteOwner(owner);
		
		RequestDispatcher view = req.getRequestDispatcher("manage.jsp");

		view.forward(req, res);
	}
}

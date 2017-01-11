package com.halcyonmobile.webadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.halcyonmobile.model.Owners;
import com.halcyonmobile.rest.OwnersService;

public class Login extends HttpServlet implements HttpSessionListener{
	private static final long serialVersionUID = 13L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession();
		
		if(session.isNew()) {
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, res);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("success.jsp");
			
			view.forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String user = req.getParameter("userName");
		String pass = req.getParameter("password");
		String privilege = "";
		boolean ok = false;
		HttpSession session = req.getSession(true);
		
		Integer visitCount = new Integer(0);
		String visitCountKey = new String("visitCount");

		System.out.println("Check cookies");
		if (session.getAttribute("user") == null) {
			System.out.println("New session created");			
			session.setAttribute(visitCountKey, 0);
		} else {
			if (null != session.getAttribute(visitCountKey)) {
				visitCount = (Integer) session.getAttribute(visitCountKey);
				visitCount = visitCount + 1;
				session.setAttribute(visitCountKey, visitCount);
				System.out.println("Visit: " + visitCount);
			} else {
				visitCount = 0;
				session.setAttribute(visitCountKey, visitCount);
			}
		}

		OwnersService os = new OwnersService();
		ArrayList<Owners> ownersList = os.findAll();

		for (Owners o : ownersList) {
			if (o.getUsername().equals(user) && o.getPassword().equals(pass)) {
				ok = true;
				privilege = o.getPrivilege();

				if (privilege.equals("1")) {
					privilege = "admin";
				} else {
					privilege = "manager";
				}

				break;
			}
		}

		if (ok) {
			session.setAttribute("ok", ok);
			session.setAttribute("user", user);
			session.setAttribute("privilege", privilege);
		} else {
			session.setAttribute("ok", ok);
		}

		RequestDispatcher view = req.getRequestDispatcher("success.jsp");

		view.forward(req, res);
	}

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		 System.out.printf("Session ID %s created at %s%n", event.getSession().getId(), new Date());	
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.printf("Session ID %s destroyed at %s%n", event.getSession().getId(), new Date());	
	}
}

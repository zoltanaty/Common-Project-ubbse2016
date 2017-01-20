package com.halcyonmobile.webadmin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.halcyonmobile.model.Owners;
import com.halcyonmobile.rest.OwnersService;

public class Login extends HttpServlet implements HttpSessionListener{
	private static final long serialVersionUID = 13L;
	
	public static String hashPassword(String password) throws UnsupportedEncodingException {
		String genPass = null;
		String salt = "tomcat";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(salt.getBytes("UTF-8"));
			byte[] bytes = md.digest(password.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			genPass = sb.toString();
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return genPass;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession(true);
		
		if(session.isNew()) {
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, res);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("results.jsp");
			
			view.forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String[] userName = req.getParameterValues("userName");
		String[] passs = req.getParameterValues("password");
		String user = userName[userName.length-1];
		String pass = hashPassword(passs[passs.length-1]);
		String privilege = "";
		boolean ok = false;
		HttpSession session = req.getSession(true);
		
		Integer visitCount = new Integer(0);
		String visitCountKey = new String("visitCount");

		System.out.println("Check cookies");
		if (session.getAttribute("user") == null) {
			System.out.println("New session created");			
			session.setAttribute(visitCountKey, 0);
			session.setMaxInactiveInterval(60 * 60);
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

		RequestDispatcher view;
		
		if(privilege == "admin") view = req.getRequestDispatcher("manage.jsp");
		else view = req.getRequestDispatcher("results.jsp");

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

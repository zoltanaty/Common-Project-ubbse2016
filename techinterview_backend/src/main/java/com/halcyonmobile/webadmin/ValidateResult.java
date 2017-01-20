package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.halcyonmobile.model.Result;
import com.halcyonmobile.rest.ResultService;

public class ValidateResult extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();

		if(session.isNew()) {
			res.sendRedirect("index.jsp");
		} else {
			int position = Integer.parseInt(req.getParameter("position"));
			int p = Integer.parseInt(req.getParameter("p"));
			String find = req.getParameter("find");
			int userId = Integer.parseInt(req.getParameter("userId"));
			int resultId = Integer.parseInt(req.getParameter("resultId"));
			boolean correct = Boolean.parseBoolean(req.getParameter("correct"));

			ResultService rs = new ResultService();
			Result update = rs.findById(resultId);
			update.setIsCorrect(correct);
			
			rs.setIsCorrect(update);

			res.sendRedirect("results.jsp?position=" + position + "&p=" + p + "&find=" + find + "#popup" + userId);
		}
	}
}
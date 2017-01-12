package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.halcyonmobile.rest.QuestionService;

public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 5624661518859972166L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String que = req.getParameter("que");
		
		QuestionService sq = new QuestionService();
		sq.deleteQuestion(que);
		
		RequestDispatcher view = req.getRequestDispatcher("manage.jsp");

		view.forward(req, res);
	}
}

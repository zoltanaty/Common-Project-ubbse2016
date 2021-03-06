package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.halcyonmobile.model.Question;
import com.halcyonmobile.rest.QuestionService;

public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 5624661518859972166L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.isNew()) {
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, res);
		
			return;
		}
		
		int que = Integer.parseInt(req.getParameter("que"));
		
		QuestionService sq = new QuestionService();
		Question question = sq.findById(que);
		sq.deleteQuestion(question);
		
		RequestDispatcher view = req.getRequestDispatcher("manage.jsp");

		view.forward(req, res);
	}
}

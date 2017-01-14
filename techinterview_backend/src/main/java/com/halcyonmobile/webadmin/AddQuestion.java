package com.halcyonmobile.webadmin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.halcyonmobile.rest.AnswerService;
import com.halcyonmobile.rest.PositionService;
import com.halcyonmobile.rest.QuestionService;
import com.halcyonmobile.rest.QuestionTypeService;

public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = -6639661737670004210L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.isNew()) {
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, res);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("manage.jsp");
			
			view.forward(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.isNew()) {
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			
			view.forward(req, res);
		} else {
			RequestDispatcher view = req.getRequestDispatcher("manage.jsp");
			
			view.forward(req, res);
		}
		
		PositionService ps = new PositionService();
		QuestionTypeService qts = new QuestionTypeService();
		QuestionService q = new QuestionService();
		AnswerService as = new AnswerService();

		String pos = req.getParameter("posList");
		String qType = req.getParameter("queType");
		String que = req.getParameter("Question");

		String answers[] = req.getParameterValues("Answer");

		int posId = ps.findByName(pos);
		int qTypeId = qts.findByName(qType);

		// onFail
		if (!qType.equals("textfield") && (answers[0].equals("") && answers[1].equals(""))) {
			RequestDispatcher view = req.getRequestDispatcher("manage.jsp");

			view.forward(req, res);

			return;
		}

		int qId = q.insertQuestion(que, qTypeId, posId);
		
		as.insertAnswer(answers, qId, false);
		
		RequestDispatcher view = req.getRequestDispatcher("manage.jsp");

		view.forward(req, res);
	}
}

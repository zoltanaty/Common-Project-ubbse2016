package com.halcyonmobile.rest;

import java.util.List;

import org.junit.Test;

import com.halcyonmobile.model.Answer;

import static org.junit.Assert.*;

public class AnswerServiceTest {
	
	private static final Integer QUESTION_ID = 3;
	private static final String ANSWER_1 = "ANSWER_1";
	private static final String ANSWER_2 = "ANSWER_2";

	@Test
	public void findAllTest() {
		AnswerService answerService = new AnswerService();
		
		List<Answer> resultList = answerService.findAll();
		assertNotEquals(0, resultList.size());
	}
	
	@Test
	public void findByQuestionTest() {
		AnswerService answerService = new AnswerService();
		
		List<Answer> resultList = answerService.findByQuestion(QUESTION_ID);
		assertNotEquals(0, resultList.size());
	}
	
	@Test
	public void inserAnswerTest() {
		AnswerService answerService = new AnswerService();
		
		String[] answers = {ANSWER_1, ANSWER_2};
		boolean[] correct = {true, false};
		
		int nrOfAnswers = answerService.findByQuestion(QUESTION_ID).size();
		
		answerService.insertAnswer(answers, QUESTION_ID, correct);
		
		List<Answer> resultList = answerService.findByQuestion(QUESTION_ID);
		assertEquals(nrOfAnswers + 2, resultList.size());
	}
	

}

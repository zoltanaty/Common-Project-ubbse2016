package com.halcyonmobile.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.halcyonmobile.model.QuestionType;

public class QuestionTypeServiceTest {
	
	private static final int QUESTION_TYPE_ID = 1;

	@Test
	public void findByQuestionTest() {
		QuestionTypeService questionTypeService = new QuestionTypeService();
		
		QuestionType result = questionTypeService.findById(QUESTION_TYPE_ID);
		
		assertEquals(QUESTION_TYPE_ID, result.getId());
	}
}

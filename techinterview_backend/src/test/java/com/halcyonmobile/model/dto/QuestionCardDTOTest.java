package com.halcyonmobile.model.dto;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.halcyonmobile.rest.QuestionCardService;

public class QuestionCardDTOTest {
	
	@Test
	public void constructorTest(){
		QuestionCardService questinCardService = new QuestionCardService();
		List<QuestionCardDTO> questionCardDTOList = questinCardService.findByPosition(1);
		QuestionCardDTO questionCardDTO = new QuestionCardDTO(questionCardDTOList.get(0).getQuestion(),questionCardDTOList.get(0).getAnswers(),questionCardDTOList.get(0).getQuestionType());
	
		assertEquals(questionCardDTOList.get(0).getQuestion(),questionCardDTO.getQuestion());
		assertEquals(questionCardDTOList.get(0).getAnswers(),questionCardDTO.getAnswers());
		assertEquals(questionCardDTOList.get(0).getQuestionType(),questionCardDTO.getQuestionType());
	}

	@Test
	public void gettersAndSettersTest(){

		QuestionCardService questinCardService = new QuestionCardService();
		List<QuestionCardDTO> questionCardDTOList = questinCardService.findByPosition(1);
		
		QuestionCardDTO questionCardDTO = new QuestionCardDTO();
		
		questionCardDTO.setAnswers(questionCardDTOList.get(0).getAnswers());
		assertEquals(questionCardDTOList.get(0).getAnswers(),questionCardDTO.getAnswers());
		
		questionCardDTO.setQuestion(questionCardDTOList.get(0).getQuestion());
		assertEquals(questionCardDTOList.get(0).getQuestion(),questionCardDTO.getQuestion());
		
		questionCardDTO.setQuestionType(questionCardDTOList.get(0).getQuestionType());
		assertEquals(questionCardDTOList.get(0).getQuestionType(),questionCardDTO.getQuestionType());
		
	}

}

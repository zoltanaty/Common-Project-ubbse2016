package com.halcyonmobile.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.halcyonmobile.model.Answer;
import com.halcyonmobile.model.Position;
import com.halcyonmobile.model.Question;
import com.halcyonmobile.model.QuestionType;
import com.halcyonmobile.model.dto.QuestionCardDTO;

@Path("/questioncard")
public class QuestionCardService {
	
	@GET
	@Path("/{id_position}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<QuestionCardDTO> findByPosition(@PathParam("id_position") Integer idPosition) {
				
		QuestionService questionService = new QuestionService();
		AnswerService answerService = new AnswerService();
		QuestionTypeService questionTypeService = new QuestionTypeService();
		
		List<QuestionCardDTO> questioncardDTOList = new ArrayList<>();
		List<Question> questionList = questionService.findByPosition(idPosition); 
		
		long seed = System.nanoTime();
		Collections.shuffle(questionList, new Random(seed));
		
		PositionService positionService = new PositionService();
		Position position = positionService.findById(idPosition);
		int imax = position.getnrQue();
	    if(questionList.size()<imax){
	    	imax = questionList.size();
	    }
	    
	    for(int i = 0; i< imax;i++){
	    	Question question = questionList.get(i);
	    	List<Answer> answers = answerService.findByQuestion(question.getId());	    	
	    	QuestionType questionType = questionTypeService.findById(question.getId_questiontype());
	    	QuestionCardDTO questionCardDTO = new QuestionCardDTO(question, answers, questionType);
	    	questioncardDTOList.add(questionCardDTO);
	    }	    
	    
	    return questioncardDTOList;
	}

}

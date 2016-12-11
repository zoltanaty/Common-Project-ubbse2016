package com.halcyonmobile.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.halcyonmobile.model.Answer;
import com.halcyonmobile.model.Question;
import com.halcyonmobile.model.QuestionType;
import com.halcyonmobile.model.dto.QuestionCardDTO;

@Path("/questioncard")
public class QuestionCardService {
	
	@GET
	@Path("/{id_position}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<QuestionCardDTO> findByPosition(@PathParam("id_position") Integer idPosition) {
		
		List<QuestionCardDTO> questioncardDTOList = new ArrayList<>();
		
		QuestionService questionService = new QuestionService();
		AnswerService answerService = new AnswerService();
		QuestionTypeService questionTypeService = new QuestionTypeService();
		
	
		List<Question> questionList = questionService.findByPosition(idPosition); 
	    
	    for(Question question : questionList){
	    	List<Answer> answers = answerService.findByQuestion(question.getId());
	    	QuestionType questionType = questionTypeService.findById(question.getId_questiontype());
	    	
	    	QuestionCardDTO questionCardDTO = new QuestionCardDTO(question, answers, questionType);
	    	questioncardDTOList.add(questionCardDTO);
	    }
	    
	    return questioncardDTOList;
	}

}

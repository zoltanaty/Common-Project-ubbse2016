package com.halcyonmobile.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		
		Random random = new Random();
		List<QuestionCardDTO> questioncardDTOList = new ArrayList<>();
		
		QuestionService questionService = new QuestionService();
		AnswerService answerService = new AnswerService();
		QuestionTypeService questionTypeService = new QuestionTypeService();
		
	
		List<Question> questionList = questionService.findByPosition(idPosition); 
	    
	    for(Question question : questionList){
	    	List<Answer> answers = answerService.findByQuestion(question.getId());
	    	
//	    	for(int i = 0; i< 10; i++){
//	    		int index = Math.abs(random.nextInt()) % (answers.size() + 1);
//	    		int index2 = Math.abs(random.nextInt()) % (answers.size() + 1);
//	    		
//	    		Answer tmp = answers.get(index);
//	    		answers.set(index, answers.get(index2));
//	    		answers.set(index2, tmp);
//	    	}
	    	
	    	
	    	QuestionType questionType = questionTypeService.findById(question.getId_questiontype());
	    	
	    	QuestionCardDTO questionCardDTO = new QuestionCardDTO(question, answers, questionType);
	    	questioncardDTOList.add(questionCardDTO);
	    }
	    
	    List<QuestionCardDTO> randomQuestionList = new ArrayList<>();
	    
	    int imax;
	    int modMax;
	    if(questioncardDTOList.size()<40){
	    	imax = questioncardDTOList.size();
	    	modMax = 5;
	    }else{
	    	imax = 40;
	    	modMax = 400;
	    }
	    
	    for(int i = 0; i< imax;i++){
	    	int rnd = Math.abs(random.nextInt()) % modMax + 1;
	    	randomQuestionList.add(questioncardDTOList.get(rnd));
	    }
	    
	    
	    
	    return randomQuestionList;
	}

}

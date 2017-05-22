package com.halcyonmobile.model.dto;

import java.util.List;

import com.halcyonmobile.model.Answer;
import com.halcyonmobile.model.Question;
import com.halcyonmobile.model.QuestionType;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuestionCardDTO {
	
	private Question question;
	private List<Answer> answers;
	private QuestionType questionType;
	
	public QuestionCardDTO(){
		super();
	}
	
	public QuestionCardDTO(Question question, List<Answer> answers, QuestionType questionType){
		this.question = question;
		this.answers = answers;
		this.questionType = questionType;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
}

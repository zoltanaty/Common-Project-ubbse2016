package com.halcyonmobile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "result")
public class Result {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id", unique=true, nullable=false)
	private int id;
	
	@Column(name = "id_user")
	private int idUser;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "thinking_time")
	private int thinkingTime;
	
	@Column(name = "is_correct")
	private Boolean isCorrect;
	
	public Result() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getThinkingTime() {
		return thinkingTime;
	}

	public void setThinkingTime(int thinkingTime) {
		this.thinkingTime = thinkingTime;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "Result [id=" + id + ", idUser=" + idUser + ", question=" + question + ", answer=" + answer + ", date="
				+ date + ", thinkingTime=" + thinkingTime + ", isCorrect=" + isCorrect + "]";
	}

	
}
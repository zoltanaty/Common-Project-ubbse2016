package com.halcyonmobile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "result")
public class Result {
	
	@Id
    @Column(name = "id")
	private int id;
	
	@Column(name = "id_user")
	private int id_user;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "thinking_time")
	private int thinking_time;
	
	@Column(name = "is_correct")
	private Boolean pertinence;
	
	public Result() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return id_user;
	}

	public void setUserId(int id_user) {
		this.id_user = id_user;
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
		return thinking_time;
	}

	public void setThinkingTime(int thinking_time) {
		this.thinking_time = thinking_time;
	}
	
	public Boolean getPertinence() {
		return pertinence;
	}
	
	public void setPertinence(Boolean pertinence) {
		this.pertinence = pertinence;
	}
}

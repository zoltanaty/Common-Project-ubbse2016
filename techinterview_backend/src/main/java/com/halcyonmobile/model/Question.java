package com.halcyonmobile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "question")
public class Question {
	
	@Id
    @Column(name = "id")
	private int id;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "id_questiontype")
	private int id_questionType;
	
	@Column(name = "id_position")
	private int id_position;
	
	public Question() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getId_questiontype() {
		return id_questionType;
	}

	public void setId_questiontype(int id_questiontype) {
		this.id_questionType = id_questiontype;
	}

	public int getId_position() {
		return id_position;
	}

	public void setId_position(int id_position) {
		this.id_position = id_position;
	}
	
}

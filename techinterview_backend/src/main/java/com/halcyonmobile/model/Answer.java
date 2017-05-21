package com.halcyonmobile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "answer")
public class Answer {

	@Id
    @Column(name = "id")
	private int id;
	
    @Column(name = "id_question")
	private int id_question;
    
    @Column(name = "answer")
    private String answer;
    
    @Column(name = "iscorrect")
    private Boolean isCorrect;
    
    public Answer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_question() {
		return id_question;
	}

	public void setId_question(int id_question) {
		this.id_question = id_question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	@Override
	public String toString() {
		return "Answer [id=" + id + ", id_question=" + id_question + ", answer=" + answer + ", is_correct=" + isCorrect + "]";
	}
	  
}

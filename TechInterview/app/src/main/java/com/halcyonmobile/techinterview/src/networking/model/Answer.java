package com.halcyonmobile.techinterview.src.networking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zmate on 12/12/2016.
 */

public class Answer implements Serializable {
    @SerializedName("id")
    private int id;
    //TODO CR: Don't mix up naming conventions - if you decided to use camelCase for variables, be consistent (applies to all model classes). [Peter]
    @SerializedName("id_question")
    private int id_question;
    @SerializedName("answer")
    private String answer;
    @SerializedName("isCorrect")
    private Boolean isCorrect;

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

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", id_question=" + id_question +
                ", answer='" + answer + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}

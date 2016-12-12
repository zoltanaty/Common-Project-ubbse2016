package com.halcyonmobile.techinterview.src.networking.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zmate on 12/12/2016.
 */

public class Answer {
    @SerializedName("id")
    private int id;
    @SerializedName("id_question")
    private int id_question;
    @SerializedName("answer")
    private String answer;

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

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", id_question=" + id_question +
                ", answer='" + answer + '\'' +
                '}';
    }
}

package com.halcyonmobile.techinterview.src.networking.model;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zmate on 1/7/2017.
 */

public class Result {

    @SerializedName("id")
    private int id;
    @SerializedName("idUser")
    private int idUser;
    @SerializedName("question")
    private String question;
    @SerializedName("answer")
    private String answer;
    @SerializedName("date")
    private String date;
    @SerializedName("thinkingTime")
    private int thinkingTime;
    @SerializedName("isCorrect")
    private Boolean isCorrect;

    public void Result(){
        //do nothing
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

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", date='" + date + '\'' +
                ", thinkingTime=" + thinkingTime +
                ", isCorrect=" + isCorrect +
                '}';
    }
}

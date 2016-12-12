package com.halcyonmobile.techinterview.src.networking.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zmate on 12/12/2016.
 */

public class Question {

    @SerializedName("id")
    private int id;
    @SerializedName("question")
    private String question;
    @SerializedName("id_questionType")
    private int id_questionType;
    @SerializedName("id_position")
    private int id_position;

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

    public int getId_questionType() {
        return id_questionType;
    }

    public void setId_questionType(int id_questionType) {
        this.id_questionType = id_questionType;
    }

    public int getId_position() {
        return id_position;
    }

    public void setId_position(int id_position) {
        this.id_position = id_position;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", id_questionType=" + id_questionType +
                ", id_position=" + id_position +
                '}';
    }
}

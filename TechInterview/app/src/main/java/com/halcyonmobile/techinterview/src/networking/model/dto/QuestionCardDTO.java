package com.halcyonmobile.techinterview.src.networking.model.dto;

import com.google.gson.annotations.SerializedName;
import com.halcyonmobile.techinterview.src.networking.model.Answer;
import com.halcyonmobile.techinterview.src.networking.model.Question;
import com.halcyonmobile.techinterview.src.networking.model.QuestionType;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zmate on 12/12/2016.
 */
// TODO CR: [Medium] For serialization on Android implement the parcelable interface, it has better performance [Gelli]
public class QuestionCardDTO implements Serializable {

    @SerializedName("question")
    private Question question;
    @SerializedName("answers")
    private List<Answer> answers;
    @SerializedName("questionType")
    private QuestionType questionType;

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

    @Override
    public String toString() {
        return "QuestionCardDTO{" +
                "question=" + question +
                ", answers=" + answers +
                ", questionType=" + questionType +
                '}';
    }
}

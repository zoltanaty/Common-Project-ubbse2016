package com.halcyonmobile.techinterview.src.networking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zmate on 12/12/2016.
 */

public class QuestionType implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "QuestionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

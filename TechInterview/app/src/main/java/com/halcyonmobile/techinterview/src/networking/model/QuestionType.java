package com.halcyonmobile.techinterview.src.networking.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zmate on 12/12/2016.
 */

public class QuestionType {

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
}

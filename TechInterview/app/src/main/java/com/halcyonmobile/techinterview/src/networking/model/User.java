package com.halcyonmobile.techinterview.src.networking.model;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by zmate on 1/7/2017.
 */

public class User {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("positionId")
    private int positionId;

    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //TODO CR: Remove unused methods (applies to all unused getters in your model classes). [Peter]

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", positionId=" + positionId +
                '}';
    }
}

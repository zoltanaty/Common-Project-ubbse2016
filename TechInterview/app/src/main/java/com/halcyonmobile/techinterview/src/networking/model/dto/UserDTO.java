package com.halcyonmobile.techinterview.src.networking.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zmate on 1/7/2017.
 */

public class UserDTO {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("positionId")
    private int positionId;

    public UserDTO() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", positionId=" + positionId +
                '}';
    }
}

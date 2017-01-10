package com.halcyonmobile.techinterview.src.networking.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zmate on 1/7/2017.
 */

public class UserDTO {
    //TODO CR: I still think that separating your DTO model classes from other models is an unnecessary complication and it would not be maintainable for larger projects. The way you
    //TODO CR: locally handle models does not have to mirror the server's data structure - if it can simplify your work, use custom deserializers. It's weird since you were the ones
    //TODO CR: who came up with the entire data structure (you didn't have to argue with remote backend developers), you could have made it more optimized for mobile communication.
    //TODO CR: If everything is working fine now there is no need to change this, so feel free to delete this comment, it's just something to consider the next time. [Peter]
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

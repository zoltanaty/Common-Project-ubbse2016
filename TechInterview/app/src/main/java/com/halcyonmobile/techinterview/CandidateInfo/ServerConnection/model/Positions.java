package com.halcyonmobile.techinterview.CandidateInfo.ServerConnection.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Szilard on 11/26/2016.
 */

// TODO CR: [Medium] Consider renaming this class to Position. [PPeter]
public class Positions {
    @SerializedName("idposition")
    private String id;
    @SerializedName("positionname")
    private String name;

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}

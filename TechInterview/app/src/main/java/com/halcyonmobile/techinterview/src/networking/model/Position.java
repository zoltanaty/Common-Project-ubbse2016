package com.halcyonmobile.techinterview.src.networking.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Szilard on 11/26/2016.
 */

public class Position {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}

package com.halcyonmobile.techinterview.src.networking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Szilard on 11/26/2016.
 */

public class Position implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("nrQe")
    private String nrQe;

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

    public String getNrQe() {
        return nrQe;
    }

    public void setNrQe(String nrQe) {
        this.nrQe = nrQe;
    }

    public Position(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

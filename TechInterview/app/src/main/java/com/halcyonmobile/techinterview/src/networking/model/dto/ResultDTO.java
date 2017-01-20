package com.halcyonmobile.techinterview.src.networking.model.dto;

import com.google.gson.annotations.SerializedName;
import com.halcyonmobile.techinterview.src.networking.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zmate on 1/7/2017.
 */

public class ResultDTO {

    @SerializedName("resultList")
    private List<Result> resultList;

    public ResultDTO() {
        resultList = new ArrayList<>();
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "resultList=" + resultList +
                '}';
    }
}

package com.halcyonmobile.techinterview.CandidateInfo.ServerConnection.service;

import com.halcyonmobile.techinterview.CandidateInfo.ServerConnection.model.Positions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Szilard on 11/26/2016.
 */

public interface APIService {
    @GET("positions")
    Call<List<Positions>> getPositionsDetails();

    @FormUrlEncoded
    @POST("my_json/insertUsingRetrofit.php")
    Call<Positions> setPeopleDetails(@Field("name") String name);
}

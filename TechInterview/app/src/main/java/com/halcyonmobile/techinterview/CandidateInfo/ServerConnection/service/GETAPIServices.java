package com.halcyonmobile.techinterview.CandidateInfo.ServerConnection.service;

import com.halcyonmobile.techinterview.CandidateInfo.ServerConnection.model.Positions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Szilard on 11/26/2016.
 */

public interface GETAPIServices {
    @GET("my_json")
    Call<List<Positions>> getPositionsDetails();
}

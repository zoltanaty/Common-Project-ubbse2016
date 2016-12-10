package com.halcyonmobile.techinterview.src.networking;

import com.halcyonmobile.techinterview.src.networking.model.Position;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Szilard on 11/26/2016.
 */

public interface IConnection {

    @GET("techinterview-backend/rest/position")
    Call<List<Position>> getPositions();

}

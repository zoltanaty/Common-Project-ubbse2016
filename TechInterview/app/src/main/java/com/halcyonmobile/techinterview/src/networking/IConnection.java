package com.halcyonmobile.techinterview.src.networking;

import com.halcyonmobile.techinterview.src.networking.model.Position;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Szilard on 11/26/2016.
 */

public interface IConnection {

    @GET("techinterview-backend/rest/position")
    Call<List<Position>> getPositions();

    @GET("techinterview-backend/rest/questioncard/{id_position}")
    Call<List<QuestionCardDTO>> getQuestionCards(@Path("id_position") Integer idPosition);

}

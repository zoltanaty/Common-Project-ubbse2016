package com.halcyonmobile.techinterview.src.networking;

import com.halcyonmobile.techinterview.src.networking.model.Position;
import com.halcyonmobile.techinterview.src.networking.model.User;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;
import com.halcyonmobile.techinterview.src.networking.model.dto.ResultDTO;
import com.halcyonmobile.techinterview.src.networking.model.dto.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Szilard on 11/26/2016.
 */

public interface IConnection {

    @GET("position")
    Call<List<Position>> getPositions();

    @GET("questioncard/{id_position}")
    Call<List<QuestionCardDTO>> getQuestionCards(@Path("id_position") Integer idPosition);

    @POST("user/")
    Call<Integer> register(@Header("Content-Type") String content_type, @Body UserDTO user);

    @GET("user/{id}")
    Call<User> getUser(@Path("id") Integer id);

    @POST("result/")
    Call<Boolean> sendResult(@Header("Content-Type") String content_type, @Body ResultDTO resultDTO);


}

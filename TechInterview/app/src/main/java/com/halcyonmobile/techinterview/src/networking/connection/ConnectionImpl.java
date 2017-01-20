package com.halcyonmobile.techinterview.src.networking.connection;

import com.halcyonmobile.techinterview.src.networking.IConnection;
import com.halcyonmobile.techinterview.src.networking.model.Position;
import com.halcyonmobile.techinterview.src.networking.model.User;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;
import com.halcyonmobile.techinterview.src.networking.model.dto.ResultDTO;
import com.halcyonmobile.techinterview.src.networking.model.dto.UserDTO;
import com.halcyonmobile.techinterview.src.utils.RetrofitSingleton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Szilard on 11/25/2016.
 */

public class ConnectionImpl implements IConnection {
    @Override
    public Call<List<Position>> getPositions() {
        Retrofit retrofit = RetrofitSingleton.getInstance();
        IConnection service = retrofit.create( IConnection.class );
        return service.getPositions();
    }

    public void getPositionList(Callback<List<Position>> callback) {
        Call<List<Position>> call = getPositions();
        call.enqueue( callback );
    }

    @Override
    public Call<List<QuestionCardDTO>> getQuestionCards(@Path("id_position") Integer idPosition) {
        Retrofit retrofit = RetrofitSingleton.getInstance();
        IConnection service = retrofit.create( IConnection.class );
        return service.getQuestionCards( idPosition );
    }

    public void getQuestionCardList(Callback<List<QuestionCardDTO>> callback, Integer idposition) {
        Call<List<QuestionCardDTO>> call = getQuestionCards( idposition );
        call.enqueue( callback );
    }

    @Override
    public Call<Integer> register(@Header("Content-Type") String content_type, @Body UserDTO user) {
        Retrofit retrofit = RetrofitSingleton.getInstance();
        IConnection service = retrofit.create( IConnection.class );
        return service.register( content_type, user );
    }

    public void registerUser(Callback<Integer> callback, UserDTO user) {
        Call<Integer> call = register( "application/json", user );
        call.enqueue( callback );
    }

    @Override
    public Call<User> getUser(@Path("id") Integer id) {
        Retrofit retrofit = RetrofitSingleton.getInstance();
        IConnection service = retrofit.create( IConnection.class );
        return service.getUser( id );
    }

    public void getRegisteredUser(Callback<User> callback, Integer id) {
        Call<User> call = getUser( id );
        call.enqueue( callback );
    }

    @Override
    public Call<Boolean> sendResult(@Header("Content-Type") String content_type, @Body ResultDTO resultDTO) {
        Retrofit retrofit = RetrofitSingleton.getInstance();
        IConnection service = retrofit.create( IConnection.class );
        return service.sendResult( content_type, resultDTO );
    }

    public void sendUserResult(Callback<Boolean> callback, ResultDTO resultDTO) {
        Call<Boolean> call = sendResult( "application/json", resultDTO );
        call.enqueue( callback );
    }
}

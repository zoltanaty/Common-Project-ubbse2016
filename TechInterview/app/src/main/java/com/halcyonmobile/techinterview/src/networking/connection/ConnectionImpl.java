package com.halcyonmobile.techinterview.src.networking.connection;

import android.util.Log;

import com.halcyonmobile.techinterview.src.networking.IConnection;
import com.halcyonmobile.techinterview.src.networking.model.Position;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

/**
 * Created by Szilard on 11/25/2016.
 */

//TODO CR: Never implement the Retrofit interface. Please read the documentation carefully, you have misunderstood some important points. [Peter]
public class ConnectionImpl implements IConnection {

    private static final String IPV4 = "192.168.1.105";
    private static final String BASEURL = "http://" + IPV4 + ":8080/";

    @Override
    public Call<List<Position>> getPositions() {
        //TODO CR: Creating the Retrofit client should only be done once throughout your app's lifecycle. This class needs to be rewritten. [Peter]

        System.out.println(BASEURL);

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder()

                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS).build())
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IConnection service = retrofit.create(IConnection.class);
        return service.getPositions();
    }

    public void getPositionList(Callback<List<Position>> callback) {
        Call<List<Position>> call = getPositions();
        call.enqueue(callback);
    }

    @Override
    public Call<List<QuestionCardDTO>> getQuestionCards(@Path("id_position") Integer idPosition) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder()

                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS).build())
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IConnection service = retrofit.create(IConnection.class);
        return service.getQuestionCards(idPosition);
    }

    public void getQuestionCardList(Callback<List<QuestionCardDTO>> callback, Integer idposition) {
        Call<List<QuestionCardDTO>> call = getQuestionCards(idposition);
        call.enqueue(callback);
    }


}

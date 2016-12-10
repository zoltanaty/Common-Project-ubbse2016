package com.halcyonmobile.techinterview.src.networking.connection;

import android.util.Log;

import com.halcyonmobile.techinterview.src.networking.IConnection;
import com.halcyonmobile.techinterview.src.networking.model.Position;

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

/**
 * Created by Szilard on 11/25/2016.
 */

public class ConnectionImpl implements IConnection {
    private List<Position> positions;

    @Override
    public Call<List<Position>> getPositions() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder()

                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS).build())
                .baseUrl("http://192.168.96.55:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IConnection service = retrofit.create(IConnection.class);
        return service.getPositions();
    }

    public List<Position> getPositionList(Callback<List<Position>> callback) {
        Call<List<Position>> call = getPositions();
        call.enqueue(callback);
        return positions;
    }
}

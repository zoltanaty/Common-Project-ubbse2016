package com.halcyonmobile.techinterview.src.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zmate on 1/7/2017.
 */

public class RetrofitSingleton {

    private static final String IPV4 = "192.168.100.8";
    private static final String BASEURL = "http://" + IPV4 + ":8080/techinterview-backend/rest/";
    private static Retrofit retrofit;

    private RetrofitSingleton() {
        //do nothing
    }

    public static Retrofit getInstance() {
        if (retrofit != null) {
            return retrofit;
        } else {
            return new Retrofit.Builder()
                    .client(new OkHttpClient.Builder()

                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS).build())
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

}

package com.halcyonmobile.techinterview.src.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zmate on 1/7/2017.
 */

public class RetrofitSingleton {
    //TODO CR: Consider moving this class to the networking package. [Peter]
    //TODO CR: We'd really like to test the app ever when you're not around, please move the server to a permanent online storage. [Peter]
    private static final String IPV4 = "192.168.96.55";
    private static final String BASEURL = "http://" + IPV4 + ":8080/techinterview-backend/rest/";
    private static Retrofit retrofit;

    //TODO CR: Good job removing the duplicated code, you're on the right track! However, some minor modifications could still improve your implementation, let's discuss this! [Peter]
    private RetrofitSingleton() {
        //do nothing
    }

    public static Retrofit getInstance() {
        if (retrofit != null) {
            return retrofit;
        } else {
            //TODO CR: You forgot to save the reference to the newly created object! Now the getInstance() method always returns a new instance... [Peter]
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

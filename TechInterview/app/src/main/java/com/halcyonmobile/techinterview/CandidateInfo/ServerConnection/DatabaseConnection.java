package com.halcyonmobile.techinterview.CandidateInfo.ServerConnection;

import android.util.Log;

import com.halcyonmobile.techinterview.CandidateInfo.ServerConnection.model.Positions;
import com.halcyonmobile.techinterview.CandidateInfo.ServerConnection.service.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Szilard on 11/25/2016.
 */

public class DatabaseConnection {
    public static String [] details = new String[1];

    private List<Positions> peopleData;

    public DatabaseConnection() {
        // TODO CR: [High] Consider using the singleton pattern for this class. [PPeter]
        peopleData = null;
        details[0]="";
        getPositions();

    }

    public  void getPositions() {
        try {
            // TODO CR: [High] There's no Exception to catch, the block is useless. [PPeter]
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.117:8888/").
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIService service = retrofit.create(APIService.class);
            Call<List<Positions>> call = service.getPositionsDetails();
            // TODO CR: [Medium] Don't put network requests in a class that's responsible for initializing the connection. [PPeter]
            call.enqueue(new Callback<List<Positions>>() {

                @Override
                public void onResponse(Call<List<Positions>> call, Response<List<Positions>> response) {
                    peopleData=response.body();
                    details = new String[peopleData.size()];
                    for (int i = 0; i < peopleData.size(); i++) {
                        String name = peopleData.get(i).getName();
                        details[i] = name + "\n";
                    }
                    //txt.setText(details[1]);
                }

                public void onFailure(Call<List<Positions>> call, Throwable t) {

                    int onFailure = Log.d("onFailure", t.toString());
                    // TODO CR: [Medium] Consider displaying the error on the UI using Snackbars. [PPeter]


                    details[0]="Sikertelen";
                }


            });
        } catch (Exception e) {

            details[0]="Sikertelen1";
            //Log.d("onResponse", "There is an error");
           // e.printStackTrace();
            // TODO CR: [High] Don't catch generic Exceptions, always try to be as specific as possible. [PPeter]
            Log.d("onResponse", "There is an error");
            e.printStackTrace();

        }
    }
    public static void resp (){
        new DatabaseConnection();

    }
}

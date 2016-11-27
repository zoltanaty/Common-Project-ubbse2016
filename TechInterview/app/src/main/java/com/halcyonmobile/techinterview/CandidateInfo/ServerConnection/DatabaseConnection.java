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
        peopleData = null;
        getPositions();

    }

    public  void getPositions() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.117:8888/").
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIService service = retrofit.create(APIService.class);
            Call<List<Positions>> call = service.getPositionsDetails();
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

                }


            });
        } catch (Exception e) {
            Log.d("onResponse", "There is an error");
            e.printStackTrace();


        }
    }

    public static void resp (){
       new DatabaseConnection();

    }
}

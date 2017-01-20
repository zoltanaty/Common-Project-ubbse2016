package com.halcyonmobile.techinterview.src.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.connection.ConnectionImpl;
import com.halcyonmobile.techinterview.src.networking.model.Position;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends Activity {
    private static ArrayAdapter<Position> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );

        int SPLASH_DISPLAY_LENGTH = 1000;
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                fillSpinner();
            }
        }, SPLASH_DISPLAY_LENGTH );
    }

    private void fillSpinner() {
        ConnectionImpl connection = new ConnectionImpl();
        connection.getPositionList( new Callback<List<Position>>() {

            @Override
            public void onResponse(Call<List<Position>> call, Response<List<Position>> response) {
                List<Position> positionList = new ArrayList<Position>();
                if (response.body() != null) {
                    for (Position position : response.body()) {
                        positionList.add( new Position( position.getId(), position.getName() ) );
                    }
                    Intent mainIntent = new Intent( SplashActivity.this, CandidateInfoActivity.class );
                    SplashActivity.this.startActivity( mainIntent );
                    SplashActivity.this.finish();
                    adapter = new ArrayAdapter<>( SplashActivity.this, R.layout.spinner_row, positionList );
                }
            }

            @Override
            public void onFailure(Call<List<Position>> call, Throwable t) {
                Intent intent = new Intent( SplashActivity.this, NoConnectionActivity.class );
                startActivity( intent );
            }
        } );
    }

    public static ArrayAdapter<Position> getAdapter() {
        return adapter;
    }

    public static void setAdapter(ArrayAdapter<Position> adapter) {
        SplashActivity.adapter = adapter;
    }

}

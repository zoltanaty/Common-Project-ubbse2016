package com.halcyonmobile.techinterview.src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.connection.ConnectionImpl;
import com.halcyonmobile.techinterview.src.networking.model.Position;
import com.halcyonmobile.techinterview.src.utils.ConnectionError;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NoConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_no_connection );
        TextView textView = (TextView) findViewById( R.id.noConnection );
        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillSpinner();
            }
        } );
    }

    private void fillSpinner() {
        ConnectionImpl connection = new ConnectionImpl();
        connection.getPositionList( new Callback<List<Position>>() {

            @Override
            public void onResponse(Call<List<Position>> call, Response<List<Position>> response) {
                List<Position> positionList = new ArrayList<Position>();

                for (Position position : response.body()) {
                    positionList.add( new Position( position.getId(), position.getName() ) );
                }
                Intent mainIntent = new Intent( NoConnectionActivity.this, CandidateInfoActivity.class );
                NoConnectionActivity.this.startActivity( mainIntent );
                NoConnectionActivity.this.finish();
                SplashActivity.setAdapter( new ArrayAdapter<>( NoConnectionActivity.this, R.layout.spinner_row, positionList ) );

            }

            @Override
            public void onFailure(Call<List<Position>> call, Throwable t) {
                View parentLayout = findViewById( R.id.activity_no_connection );
                ConnectionError conectionError = new ConnectionError();
                conectionError.noConnection( parentLayout );
            }
        } );
    }
}

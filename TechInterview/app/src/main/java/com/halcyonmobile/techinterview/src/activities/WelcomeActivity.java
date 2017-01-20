package com.halcyonmobile.techinterview.src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.connection.ConnectionImpl;
import com.halcyonmobile.techinterview.src.networking.model.User;
import com.halcyonmobile.techinterview.src.networking.model.dto.UserDTO;
import com.halcyonmobile.techinterview.src.utils.ConnectionError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private User registeredUser;
    private UserDTO newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_welcome );
        newUser = new UserDTO();
        TextView welcomeText = (TextView) findViewById( R.id.welcome_text );
        welcomeText.setText( welcomeText.getText() + " " + getIntent().getStringExtra( "candidateName" ) );
        newUser.setName( getIntent().getStringExtra( "candidateName" ) );
        newUser.setEmail( getIntent().getStringExtra( "candidateEmail" ) );
        newUser.setPositionId( Integer.parseInt( getIntent().getStringExtra( "selectedPositionId" ) ) );
        Button btnTakeSelfie = (Button) findViewById( R.id.selfie );
        btnTakeSelfie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent takePictureIntent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                if (takePictureIntent.resolveActivity( getPackageManager() ) != null) {
                    startActivityForResult( takePictureIntent, REQUEST_IMAGE_CAPTURE );
                }
            }
        } );

        Button btnStartQuestionnaire = (Button) findViewById( R.id.start_questionnaire );
        btnStartQuestionnaire.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser( newUser );

            }
        } );
    }

    private void registerUser(UserDTO user) {
        final ConnectionImpl connection = new ConnectionImpl();
        connection.registerUser( new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer userId = response.body();

                connection.getRegisteredUser( new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        registeredUser = response.body();
                        Intent intent = new Intent( WelcomeActivity.this, QuestionnaireActivity.class );
                        intent.putExtra( "selectedPositionId", getIntent().getStringExtra( "selectedPositionId" ) );
                        intent.putExtra( "userId", registeredUser.getId() + "" );
                        startActivity( intent );
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        View parentLayout = findViewById( R.id.activity_welcome );
                        ConnectionError conectionError = new ConnectionError();
                        conectionError.noConnection( parentLayout );
                    }
                }, userId );
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                View parentLayout = findViewById( R.id.activity_welcome );
                ConnectionError conectionError = new ConnectionError();
                conectionError.noConnection( parentLayout );
            }
        }, user );
    }
}

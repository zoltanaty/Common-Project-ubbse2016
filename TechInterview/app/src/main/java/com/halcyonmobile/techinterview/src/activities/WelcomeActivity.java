package com.halcyonmobile.techinterview.src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;

public class WelcomeActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView welcomeText = (TextView) findViewById(R.id.welcome_text);
        welcomeText.setText(welcomeText.getText() + " " + getIntent().getStringExtra("candidateName"));
        Button btnTakeSelfie = (Button) findViewById(R.id.selfie);
        btnTakeSelfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                }
            }
        });
        Button btnstartQuestionar = (Button) findViewById(R.id.start_questionnaire);
        btnstartQuestionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WelcomeActivity.this, QuestionareActivity.class);
                intent.putExtra("selectedPositionId", getIntent().getStringExtra("selectedPositionId"));

                WelcomeActivity.this.startActivity(intent);
            }
        });
    }
}

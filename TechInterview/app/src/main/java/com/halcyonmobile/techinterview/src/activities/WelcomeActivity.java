package com.halcyonmobile.techinterview.src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;

public class WelcomeActivity extends AppCompatActivity {
    // TODO CR: [Low] Always take care of lint warnings.This variables can be converted to local variables[Gelli]
    private TextView welcomeText;
    private Button btnstartQuestionar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText(welcomeText.getText() + " " + getIntent().getStringExtra("candidateName"));
        btnstartQuestionar = (Button) findViewById(R.id.startQuestionar);
        btnstartQuestionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WelcomeActivity.this,QuestionareActivity.class);
                WelcomeActivity.this.startActivity(intent);
            }
        });
    }
}

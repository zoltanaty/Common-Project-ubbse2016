package com.halcyonmobile.techinterview.src.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;

public class NoConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);
        TextView textView = (TextView) findViewById(R.id.noConnection);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoConnectionActivity.this, CandidateInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}

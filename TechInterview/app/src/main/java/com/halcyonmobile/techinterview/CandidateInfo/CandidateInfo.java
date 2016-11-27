package com.halcyonmobile.techinterview.CandidateInfo;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.halcyonmobile.techinterview.CandidateInfo.Validator.MyTextWatcher;
import com.halcyonmobile.techinterview.R;

import static com.halcyonmobile.techinterview.CandidateInfo.ServerConnection.DatabaseConnection.details;
import static com.halcyonmobile.techinterview.CandidateInfo.Validator.Validator.validateEmail;
import static com.halcyonmobile.techinterview.CandidateInfo.Validator.Validator.validateName;

public class CandidateInfo extends AppCompatActivity {


    public static EditText inputName, inputEmail;
    public static TextInputLayout inputLayoutName, inputLayoutEmail;
    public static Button btnDone;
    public static String NAME;
    public static String EMAIL;
    public Spinner spinner ;
    public TextView txt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_info);
        NAME=getString(R.string.err_msg_name);
        EMAIL=getString(R.string.err_msg_email);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        btnDone = (Button) findViewById(R.id.btn_signup);
        btnDone.setEnabled(false);
        txt= (TextView) findViewById(R.id.textView2);
        spinner=(Spinner) findViewById(R.id.spinner);
        spinner();

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputName.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            public void onFocusChange( View v, boolean hasFocus ) {
                inputLayoutName.setHintEnabled(false);
            }

        } );
        inputEmail.setOnFocusChangeListener( new View.OnFocusChangeListener() {

            public void onFocusChange( View v, boolean hasFocus ) {
                inputLayoutEmail.setHintEnabled(false);

            }

        } );

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

    }

    private void spinner(){
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(CandidateInfo.this, android.R.layout.simple_spinner_item, details );
        spinner.setAdapter(adapter);
    }
    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }
        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }
}

package com.halcyonmobile.techinterview.CandidateInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.halcyonmobile.techinterview.CandidateInfo.Validator.Validator;
import com.halcyonmobile.techinterview.R;

import static com.halcyonmobile.techinterview.CandidateInfo.ServerConnection.DatabaseConnection.details;

// TODO CR: [Medium] Consider renaming this class to CandidateInfoActivity. [PPeter]
public class CandidateInfo extends AppCompatActivity {


    // TODO CR: [High] Never use static modifier for Context-related fields. [PPeter]
    private static EditText inputName;
    private static EditText inputEmail;
    private static TextInputLayout inputLayoutName;
    private static TextInputLayout inputLayoutEmail;
    private static Button btnDone;
    // TODO CR: [Medium] Don't use all-caps names for fields that aren't constants. [PPeter]
    public static String NAME;
    public static String EMAIL;
    private Spinner spinner ;

    // TODO CR: [Medium] Remove useless annotation (and methods). [PPeter]
    @org.jetbrains.annotations.Contract(pure = true)
    public static EditText inputName() {
        return inputName;
    }
    public static EditText inputEmail() {
        return inputEmail;
    }
    public static TextInputLayout inputLayoutName() {
        return inputLayoutName;
    }
    public static TextInputLayout inputLayoutEmail() {
        return inputLayoutEmail;
    }
    public static Button btnDone() {
        return btnDone;
    }

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
        // TODO CR: [Medium] Setting the enabled state can be done from the XML as well. [PPeter]
        btnDone.setEnabled(false);
        spinner=(Spinner) findViewById(R.id.spinner);
        spinner();
        Validator.listener ();
        btnDone().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(CandidateInfo.this,Welcome.class);
                CandidateInfo.this.startActivity(mainIntent);
            }
        });


    }

    private void spinner(){
        ArrayAdapter<String> adapter;
        // TODO CR: [Medium] No need to specify the type. [PPeter]
        adapter = new ArrayAdapter<String>(CandidateInfo.this, android.R.layout.simple_spinner_item, details );
        spinner.setAdapter(adapter);
    }


}

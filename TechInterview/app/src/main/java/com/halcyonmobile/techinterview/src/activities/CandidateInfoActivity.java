package com.halcyonmobile.techinterview.src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.model.Position;
import com.halcyonmobile.techinterview.src.utils.MyTextWatcher;
import com.halcyonmobile.techinterview.src.utils.ValidatorUtils;

public class CandidateInfoActivity extends AppCompatActivity {

    private EditText fieldName;
    private EditText fieldEmail;
    private TextInputLayout nameLayout;
    private TextInputLayout emailLayout;
    private Spinner spinner;
    private Button btnDone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_candidate_info );

        fieldName = (EditText) findViewById( R.id.input_name );
        fieldEmail = (EditText) findViewById( R.id.input_email );
        nameLayout = (TextInputLayout) findViewById( R.id.input_layout_name );
        emailLayout = (TextInputLayout) findViewById( R.id.input_layout_email );
        btnDone = (Button) findViewById( R.id.btn_signup );
        spinner = (Spinner) findViewById( R.id.spinner );

        fieldName.setHint( "Candidate Name" );
        fieldEmail.setHint( "Candidate Email" );

        spinner.setAdapter( SplashActivity.getAdapter() );
        setUpListeners();

        btnDone.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Position selectedPosition = (Position) spinner.getSelectedItem();
                String selectedPositionId = selectedPosition.getId().toString();
                Intent intent = new Intent( CandidateInfoActivity.this, WelcomeActivity.class );
                intent.putExtra( "candidateName", fieldName.getText().toString() );
                intent.putExtra( "candidateEmail", fieldEmail.getText().toString() );
                intent.putExtra( "selectedPositionId", selectedPositionId );

                startActivity( intent );
            }
        } );
    }

    private void setUpListeners() {
        fieldName.addTextChangedListener( new MyTextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ValidatorUtils.isValidName( charSequence.toString() )) {
                    nameLayout.setErrorEnabled( false );
                } else {
                    if (!TextUtils.isEmpty( charSequence )) {
                        nameLayout.setError( getString( R.string.candidate_info_activity_candidate_name ) );
                    } else {
                        nameLayout.setErrorEnabled( false );
                    }
                }

                setEnabledOrDisabledDoneButton();
            }
        } );

        fieldEmail.addTextChangedListener( new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ValidatorUtils.isValidEmail( charSequence.toString() )) {
                    emailLayout.setErrorEnabled( false );
                } else {
                    if (!TextUtils.isEmpty( charSequence )) {
                        emailLayout.setError( getString( R.string.candidate_info_activity_candidate_email ) );
                    } else {
                        emailLayout.setErrorEnabled( false );
                    }
                }

                setEnabledOrDisabledDoneButton();
            }
        } );
    }

    private void setEnabledOrDisabledDoneButton() {
        if (ValidatorUtils.isValidName( fieldName.getText().toString() ) && ValidatorUtils.isValidEmail( fieldEmail.getText().toString() )) {
            btnDone.setEnabled( true );
        } else {
            btnDone.setEnabled( false );
        }
    }

}
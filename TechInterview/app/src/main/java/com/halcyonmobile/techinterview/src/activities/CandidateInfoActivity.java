package com.halcyonmobile.techinterview.src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.IConnection;
import com.halcyonmobile.techinterview.src.networking.connection.ConnectionImpl;
import com.halcyonmobile.techinterview.src.networking.model.Position;
import com.halcyonmobile.techinterview.src.utils.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CandidateInfoActivity extends AppCompatActivity {

    private EditText fieldName;
    private EditText fieldEmail;
    private TextInputLayout nameLayout;
    private TextInputLayout emailLayout;
    private Spinner spinner;
    private Button btnDone;

    public String errorMsgName;
    public String errorMsgEmail;

    public EditText getFieldName() {
        return fieldName;
    }

    public EditText getFieldEmail() {
        return fieldEmail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_info);

        errorMsgName = getString(R.string.candidate_info_activity_err_msg_name);
        errorMsgEmail = getString(R.string.candidate_info_activity_err_msg_email);

        fieldName = (EditText) findViewById(R.id.input_name);
        fieldEmail = (EditText) findViewById(R.id.input_email);
        nameLayout = (TextInputLayout) findViewById(R.id.input_layout_name);
        emailLayout = (TextInputLayout) findViewById(R.id.input_layout_email);
        btnDone = (Button) findViewById(R.id.btn_signup);
        spinner = (Spinner) findViewById(R.id.spinner);

        fieldName.setHint("Candidate Name");
        fieldEmail.setHint("Candidate Email");

        fillSpinner();
        setUpListeners();

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CandidateInfoActivity.this, WelcomeActivity.class);
                intent.putExtra("candidateName", fieldName.getText().toString());

                Intent emailIntent = new Intent(CandidateInfoActivity.this, WelcomeActivity.class);
                intent.putExtra("candidateEmail", fieldEmail.getText().toString());

                Position selectedPosition = (Position)spinner.getSelectedItem();
                String selectedPositionId = selectedPosition.getId().toString();

                Intent positionIntent = new Intent(CandidateInfoActivity.this, WelcomeActivity.class);
                intent.putExtra("selectedPositionId", selectedPositionId);

                CandidateInfoActivity.this.startActivity(intent);
            }
        });
    }

    private void fillSpinner() {
        ConnectionImpl connection = new ConnectionImpl();
        connection.getPositionList(new Callback<List<Position>>() {

            @Override
            public void onResponse(Call<List<Position>> call, Response<List<Position>> response) {
                List<Position> positionList = new ArrayList<Position>();

                for (Position position : response.body()) {
                    positionList.add(new Position(position.getId(), position.getName()));
                }

                ArrayAdapter<Position> adapter;
                adapter = new ArrayAdapter<Position>(CandidateInfoActivity.this, R.layout.spinner_row, positionList);
                spinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Position>> call, Throwable t) {
                int onFailure = Log.e("Failed to load Position", t.toString());
                // TODO CR: [Medium] Consider displaying the error on the UI using Snackbars. [PPeter]
            }
        });
    }

    private void setUpListeners() {

        fieldName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Validator.isValidName(charSequence.toString())) {
                    nameLayout.setErrorEnabled(false);
                } else {
                    if (charSequence.length() > 0) {
                        nameLayout.setError(getString(R.string.candidate_info_activity_candidate_name));
                    } else {
                        nameLayout.setErrorEnabled(false);
                    }
                }

                setEnabledOrDisabledDoneButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        fieldEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Validator.isValidEmail(charSequence.toString())) {
                    emailLayout.setErrorEnabled(false);
                } else {
                    if (charSequence.length() > 0) {
                        emailLayout.setError(getString(R.string.candidate_info_activity_candidate_email));
                    } else {
                        emailLayout.setErrorEnabled(false);
                    }
                }

                setEnabledOrDisabledDoneButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        fieldName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    fieldName.setHint("");
                } else {
                    fieldName.setHint("Candidate Name");
                }
            }
        });

        fieldEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    fieldEmail.setHint("");
                } else {
                    fieldEmail.setHint("Candidate Email");
                }
            }
        });
    }

    private void setEnabledOrDisabledDoneButton() {
        if (Validator.isValidName(fieldName.getText().toString()) && Validator.isValidEmail(fieldEmail.getText().toString())) {
            btnDone.setEnabled(true);
        } else {
            btnDone.setEnabled(false);
        }
    }
}

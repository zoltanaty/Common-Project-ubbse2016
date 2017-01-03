package com.halcyonmobile.techinterview.src.activities;

import android.content.Intent;
import android.os.Bundle;
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
import com.halcyonmobile.techinterview.src.networking.connection.ConnectionImpl;
import com.halcyonmobile.techinterview.src.networking.model.Position;
import com.halcyonmobile.techinterview.src.utils.Validator;

import java.util.ArrayList;
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

    // TODO CR: [Low] Remove unused methods [Gelli]
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

        //TODO CR: I can't find where these variables are used, please double-check and make sure that you need them. [Peter]
        errorMsgName = getString(R.string.candidate_info_activity_err_msg_name);
        errorMsgEmail = getString(R.string.candidate_info_activity_err_msg_email);

        fieldName = (EditText) findViewById(R.id.input_name);
        fieldEmail = (EditText) findViewById(R.id.input_email);
        nameLayout = (TextInputLayout) findViewById(R.id.input_layout_name);
        emailLayout = (TextInputLayout) findViewById(R.id.input_layout_email);
        btnDone = (Button) findViewById(R.id.btn_signup);
        spinner = (Spinner) findViewById(R.id.spinner);

        // TODO CR: [Low]As a general rule, always set the properties from xml if possible. You can set the hint from xml to the EditText[Gelli]
        //TODO CR: Don't hardcode strings that appear on the UI, these should be read from the strings.xml file. [Peter]
        fieldName.setHint("Candidate Name");
        fieldEmail.setHint("Candidate Email");

        fillSpinner();
        setUpListeners();

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Position selectedPosition = (Position) spinner.getSelectedItem();
                String selectedPositionId = selectedPosition.getId().toString();

                //TODO CR: You could use method-chaining to avoid declaring the intent variable. [Peter]
                Intent intent = new Intent(CandidateInfoActivity.this, WelcomeActivity.class);
                // TODO CR: [Low] If you using constant values (eg. candidateName) in multiple places, consider to save them into a constant, and use that instance everywhere[Gelli]
                intent.putExtra("candidateName", fieldName.getText().toString());
                intent.putExtra("candidateEmail", fieldEmail.getText().toString());
                intent.putExtra("selectedPositionId", selectedPositionId);

                //TODO CR: There is no need to specify the "CandidateInfoActivity.this." part.  [Peter]
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
                //TODO CR: Pay attention to Lint warnings, there is no need to specify the type argument. [Peter]
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
        //TODO CR: You could create an abstract class that implements the TextWatcher interface, that way you could avoid having to override all three methods all the time. [Peter]
        fieldName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //TODO CR: Instead of enabling and disabling the error, set it to null or the error string. Disabling the error functionality also hides the TextView where the error
                //TODO CR: would have been displayed which causes the TextInputLayout to change its height. [Peter]
                if (Validator.isValidName(charSequence.toString())) {
                    nameLayout.setErrorEnabled(false);
                } else {
                    //TODO CR: Checking the length of the string should be part of the Validator. Also, as a general rule, use TextUtils.isEmpty() since it also performs a null-check. [Peter]
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

        //TODO CR: There is no need for this functionality, displaying the hint has nothing to do with the focused state of the input field. [Peter]
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
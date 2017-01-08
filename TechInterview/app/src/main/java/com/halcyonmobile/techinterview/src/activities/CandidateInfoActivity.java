package com.halcyonmobile.techinterview.src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.connection.ConnectionImpl;
import com.halcyonmobile.techinterview.src.networking.model.Position;
import com.halcyonmobile.techinterview.src.utils.MyTextWatcher;
import com.halcyonmobile.techinterview.src.utils.ValidatorUtils;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_info);

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

                startActivity(intent);
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

                ArrayAdapter<Position> adapter = new ArrayAdapter<>(CandidateInfoActivity.this, R.layout.spinner_row, positionList);
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
        fieldName.addTextChangedListener(new  MyTextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //TODO CR: Instead of enabling and disabling the error, set it to null or the error string. Disabling the error functionality also hides the TextView where the error
                //TODO CR: would have been displayed which causes the TextInputLayout to change its height. [Peter]
                if (ValidatorUtils.isValidName(charSequence.toString())) {
                    nameLayout.setErrorEnabled(false);
                } else {
                    if (!TextUtils.isEmpty(charSequence)) {
                        nameLayout.setError(getString(R.string.candidate_info_activity_candidate_name));
                    } else {
                        nameLayout.setErrorEnabled(false);
                    }
                }

                setEnabledOrDisabledDoneButton();
            }
        });

        fieldEmail.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ValidatorUtils.isValidEmail(charSequence.toString())) {
                    emailLayout.setErrorEnabled(false);
                } else {
                    if (!TextUtils.isEmpty(charSequence)) {
                        emailLayout.setError(getString(R.string.candidate_info_activity_candidate_email));
                    } else {
                        emailLayout.setErrorEnabled(false);
                    }
                }

                setEnabledOrDisabledDoneButton();
            }
        });
    }

    private void setEnabledOrDisabledDoneButton() {
        if (ValidatorUtils.isValidName(fieldName.getText().toString()) && ValidatorUtils.isValidEmail(fieldEmail.getText().toString())) {
            btnDone.setEnabled(true);
        } else {
            btnDone.setEnabled(false);
        }
    }
}
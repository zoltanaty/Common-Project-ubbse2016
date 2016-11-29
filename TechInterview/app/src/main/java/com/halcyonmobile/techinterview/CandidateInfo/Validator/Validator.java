package com.halcyonmobile.techinterview.CandidateInfo.Validator;

import android.text.TextUtils;
import android.view.View;

import com.halcyonmobile.techinterview.CandidateInfo.CandidateInfo;

public class Validator extends CandidateInfo {
    // TODO CR: [High] This should be a helper class that must not be instantiated (don't extend an Activity). [PPeter]

    // TODO CR: [High] Pass important values as parameters instead of relying on global variables. [PPeter]
    public static boolean validateName() {
        if (inputName().getText().toString().trim().isEmpty()) {
            inputLayoutName().setError(NAME);
            inputName().requestFocus();
            return false;
        } else {
            inputLayoutName().setErrorEnabled(false);
            doneButton();
        }

        return true;
    }
    public static boolean validateEmail() {
        String email = inputEmail().getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail().setError(EMAIL);
            inputEmail().requestFocus();
            return false;
        } else {
            inputLayoutEmail().setErrorEnabled(false);
            doneButton();
        }

        return true;
    }
    public static void doneButton(){
        String email = inputEmail().getText().toString().trim();
        if(isValidEmail(email)&&!email.isEmpty()&&!(inputName().getText().toString().trim().isEmpty())){
            btnDone().setEnabled(true);
        }
        else{
            btnDone().setEnabled(false);
        }
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    // TODO CR: [High] This method should belong to the Activity while the others don't. [PPeter]
    public static void listener(){
        inputName().addTextChangedListener(new MyTextWatcher(inputName()));
        inputEmail().addTextChangedListener(new MyTextWatcher(inputEmail()));
        inputName().setOnFocusChangeListener(new View.OnFocusChangeListener(){

            public void onFocusChange( View v, boolean hasFocus ) {
                inputLayoutName().setHintEnabled(false);
            }

        } );
        inputEmail().setOnFocusChangeListener( new View.OnFocusChangeListener() {

            public void onFocusChange( View v, boolean hasFocus ) {
                inputLayoutEmail().setHintEnabled(false);

            }

        } );


    }
}

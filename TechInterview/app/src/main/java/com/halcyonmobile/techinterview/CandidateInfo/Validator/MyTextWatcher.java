package com.halcyonmobile.techinterview.CandidateInfo.Validator;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.halcyonmobile.techinterview.R;

// TODO CR: [High] Only extend Activities if you intend to use the children as Activities. [PPeter]
public class MyTextWatcher extends Validator  implements TextWatcher {

    private View view;

    public MyTextWatcher(View view) {
        this.view = view;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {
        switch (view.getId()) {
            case R.id.input_name:
                validateName();
                break;
            case R.id.input_email:
                validateEmail();
                break;
        }
    }
}
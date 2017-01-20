package com.halcyonmobile.techinterview.src.utils;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by zmate on 1/8/2017.
 */

public abstract class MyTextWatcher implements TextWatcher {

    public MyTextWatcher() {
        super();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}

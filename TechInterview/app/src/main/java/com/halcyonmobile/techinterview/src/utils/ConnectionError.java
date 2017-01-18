package com.halcyonmobile.techinterview.src.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Szilard on 1/18/2017.
 */

public class ConnectionError {

    public void noConnection (View parentLayout){
        Snackbar.make(parentLayout, "Whoops! Slow or no internet connection. Please check your Wi-Fi and try again.", Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .setActionTextColor(Color.WHITE)
                .show();
    }
}

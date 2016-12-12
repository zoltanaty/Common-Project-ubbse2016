package com.halcyonmobile.techinterview.src.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.connection.ConnectionImpl;
import com.halcyonmobile.techinterview.src.networking.model.Position;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.halcyonmobile.techinterview.R.id.spinner;

public class QuestionareActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);

        String selectedPositionId =  getIntent().getStringExtra("selectedPositionId");
        System.out.println("Selected Position Id: " + selectedPositionId);
        getQuestionCardList(Integer.parseInt(selectedPositionId));
    }

    private void getQuestionCardList(Integer selectedPositionId) {
        ConnectionImpl connection = new ConnectionImpl();
        connection.getQuestionCardList(new Callback<List<QuestionCardDTO>>(){

            @Override
            public void onResponse(Call<List<QuestionCardDTO>> call, Response<List<QuestionCardDTO>> response) {
                List<QuestionCardDTO> cardList = response.body();
                System.out.println(cardList);
            }

            @Override
            public void onFailure(Call<List<QuestionCardDTO>> call, Throwable t) {
                int onFailure = Log.e("Failed to QuestionCards", t.toString());
            }
        }, selectedPositionId);

    }
}

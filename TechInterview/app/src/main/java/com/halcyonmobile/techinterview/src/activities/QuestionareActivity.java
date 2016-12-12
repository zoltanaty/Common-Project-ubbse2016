package com.halcyonmobile.techinterview.src.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.connection.ConnectionImpl;
import com.halcyonmobile.techinterview.src.networking.model.Position;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;
import com.halcyonmobile.techinterview.src.utils.FragmentAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.halcyonmobile.techinterview.R.id.spinner;

public class QuestionareActivity extends FragmentActivity {

    private ViewPager mPager;

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

                List<Fragment> fragmentList = processQuestionCardDTO(cardList);
                mPager = (ViewPager) findViewById(R.id.viewpager);
                FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
                mPager.setAdapter(fragmentAdapter);
            }

            @Override
            public void onFailure(Call<List<QuestionCardDTO>> call, Throwable t) {
                int onFailure = Log.e("Failed to QuestionCards", t.toString());
            }
        }, selectedPositionId);
    }

    private List<Fragment> processQuestionCardDTO(List<QuestionCardDTO> cardList){
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        for(QuestionCardDTO card : cardList){
            if(card.getQuestionType().getName().equals("checkbox")){
                FragmentCheckboxes frag = new FragmentCheckboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", card);
                frag.setArguments(xBundle);
                fragmentList.add(frag);
            }else if(card.getQuestionType().getName().equals("radiobutton")){
                FragmentRadioboxes frag = new FragmentRadioboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", card);
                frag.setArguments(xBundle);
                fragmentList.add(frag);
            }else if(card.getQuestionType().getName().equals("textfield")){
                FragmentTextSimple frag = new FragmentTextSimple();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", card);
                frag.setArguments(xBundle);
                fragmentList.add(frag);
            }
        }

        return fragmentList;
    }
}

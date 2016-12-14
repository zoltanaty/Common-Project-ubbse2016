package com.halcyonmobile.techinterview.src.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.connection.ConnectionImpl;
import com.halcyonmobile.techinterview.src.networking.model.Answer;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;
import com.halcyonmobile.techinterview.src.utils.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionareActivity extends FragmentActivity implements FragmentRadioboxes.ActivityCallbacks {

    private ViewPager mPager;
    private TextView timerEditText;

    private int actualQuestion = 0;
    private int allQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);

        String selectedPositionId =  getIntent().getStringExtra("selectedPositionId");
       // System.out.println("Selected Position Id: " + selectedPositionId);
        getQuestionCardList(Integer.parseInt(selectedPositionId));

        timerEditText = (TextView) findViewById(R.id.textViewTimer);

        new CountDownTimer(1800000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) ((millisUntilFinished / 1000) % 60);
                int minutes = (int) ((millisUntilFinished / 1000) / 60);
                timerEditText.setText(minutes + ":" + seconds);
            }

            public void onFinish() {
                timerEditText.setText("00:00");
            }

        }.start();
    }

    private void getQuestionCardList(Integer selectedPositionId) {
        ConnectionImpl connection = new ConnectionImpl();
        connection.getQuestionCardList(new Callback<List<QuestionCardDTO>>(){

            @Override
            public void onResponse(Call<List<QuestionCardDTO>> call, Response<List<QuestionCardDTO>> response) {
                List<QuestionCardDTO> cardList = response.body();

                int orderNumber = 1;
                for(QuestionCardDTO questionCardDTO : cardList){
                    questionCardDTO.getQuestion().setId(orderNumber++);
                }

                List<Fragment> fragmentList = processQuestionCardDTO(cardList);

                mPager = (ViewPager) findViewById(R.id.viewpager);
                mPager.setClipToPadding(false);
                mPager.setPageMargin(36);
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
        allQuestions = cardList.size();

        for(QuestionCardDTO card : cardList){
            if(card.getQuestionType().getName().equals("checkbox")){
                actualQuestion++;
                FragmentCheckboxes frag = new FragmentCheckboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", card);
                xBundle.putSerializable("orderNumber", actualQuestion);
                frag.setArguments(xBundle);
                fragmentList.add(frag);
            }else if(card.getQuestionType().getName().equals("radiobutton")){
                actualQuestion++;
                FragmentRadioboxes frag = new FragmentRadioboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", card);
                xBundle.putSerializable("orderNumber", actualQuestion);
                frag.setArguments(xBundle);
                fragmentList.add(frag);
            }else if(card.getQuestionType().getName().equals("textfield")){
                actualQuestion++;
                FragmentTextSimple frag = new FragmentTextSimple();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", card);
                xBundle.putSerializable("orderNumber", actualQuestion);
                frag.setArguments(xBundle);
                fragmentList.add(frag);
            }
        }

        return fragmentList;
    }

    @Override
    public void onQuestionAnswered(Answer answer) {
        // TODO Complete implementation! Process the provided answer.
    }
}

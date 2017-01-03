package com.halcyonmobile.techinterview.src.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
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
    private TextView actualQuestionNumber;
    private Button doneButton;
    private RecyclerView recyclerView;

    //TODO CR: Integers default to 0, no need to specify this. [Peter]
    private int actualQuestion = 0;
    private int allQuestions = 0;

    private List<Button> unansweredButtonList;
    private List<Boolean> isAnsweredList;
    private int actualQuestionNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);

        String selectedPositionId = getIntent().getStringExtra("selectedPositionId");
        getQuestionCardList(Integer.parseInt(selectedPositionId));

        timerEditText = (TextView) findViewById(R.id.textViewTimer);

        actualQuestionNumber = (TextView) findViewById(R.id.textViewQuestions);
        doneButton = (Button) findViewById(R.id.done);

        unansweredButtonList = new ArrayList<>();
        isAnsweredList = new ArrayList<>();

        GridLayout layout = (GridLayout) findViewById(R.id.gridLayout);
        LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,dpToPx(15),dpToPx(15));
        params.width=dpToPx(42);
        params.height=dpToPx(42);

        for (int j = 1; j < 40; j++) {
            Button btn = new Button(getBaseContext());
            btn.setText(j + "");
            btn.setId(j + 1);
            btn.setBackground(getDrawable(R.drawable.button_done));
            btn.setLayoutParams(params);
            btn.setTextSize(12);
            btn.setTextColor(getResources().getColor(R.color.colorPrimary));
            layout.addView(btn);
            btn.setVisibility(Button.INVISIBLE);
            unansweredButtonList.add(btn);

            Boolean currentvalue = false;
            isAnsweredList.add(currentvalue);
        }
        
        unansweredButtonList.get(0).setVisibility(Button.VISIBLE);


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
        connection.getQuestionCardList(new Callback<List<QuestionCardDTO>>() {

            @Override
            public void onResponse(Call<List<QuestionCardDTO>> call, Response<List<QuestionCardDTO>> response) {
                List<QuestionCardDTO> cardList = response.body();

                int orderNumber = 1;
                for (QuestionCardDTO questionCardDTO : cardList) {
                    questionCardDTO.getQuestion().setId(orderNumber++);
                }

                List<Fragment> fragmentList = processQuestionCardDTO(cardList);

                mPager = (ViewPager) findViewById(R.id.viewpager);
                mPager.setClipToPadding(false);
                mPager.setPageMargin(36);
                FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
                mPager.setAdapter(fragmentAdapter);

                mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                    @Override
                    public void onPageSelected(int i) {
                        actualQuestionNumber.setText((i + 1) + "/" + allQuestions);
                        actualQuestionNr = i;

                        if ((i + 1) == allQuestions) {
                            doneButton.setVisibility(Button.VISIBLE);
                        } else {
                            doneButton.setVisibility(Button.INVISIBLE);
                        }

                        Button actualButton = unansweredButtonList.get(i);

                        if(isAnsweredList.get(i) == false){
                            actualButton.setVisibility(Button.VISIBLE);
                        }
                    }

                    @Override
                    public void onPageScrolled(int arg0, float arg1, int arg2) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onPageScrollStateChanged(int arg0) {
                        // TODO Auto-generated method stub

                    }
                });
            }

            @Override
            public void onFailure(Call<List<QuestionCardDTO>> call, Throwable t) {
                int onFailure = Log.e("Failed to QuestionCards", t.toString());
            }
        }, selectedPositionId);
    }

    private List<Fragment> processQuestionCardDTO(List<QuestionCardDTO> cardList) {
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        allQuestions = cardList.size();
        actualQuestionNumber.setText("1/" + allQuestions);

        for (QuestionCardDTO card : cardList) {
            if (card.getQuestionType().getName().equals("checkbox")) {
                actualQuestion++;
                FragmentCheckboxes frag = new FragmentCheckboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", card);
                xBundle.putSerializable("orderNumber", actualQuestion);
                frag.setArguments(xBundle);
                fragmentList.add(frag);
            } else if (card.getQuestionType().getName().equals("radiobutton")) {
                actualQuestion++;
                FragmentRadioboxes frag = new FragmentRadioboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", card);
                xBundle.putSerializable("orderNumber", actualQuestion);
                frag.setArguments(xBundle);
                fragmentList.add(frag);
            } else if (card.getQuestionType().getName().equals("textfield")) {
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
        System.out.println(answer);

        unansweredButtonList.get(actualQuestionNr).setVisibility(Button.INVISIBLE);
        isAnsweredList.set(actualQuestionNr,true);
    }

    private int pxToDp(int px) {
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}

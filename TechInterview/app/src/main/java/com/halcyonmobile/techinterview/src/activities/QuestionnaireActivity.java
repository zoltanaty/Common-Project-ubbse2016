package com.halcyonmobile.techinterview.src.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayout;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.connection.ConnectionImpl;
import com.halcyonmobile.techinterview.src.networking.model.Answer;
import com.halcyonmobile.techinterview.src.networking.model.Result;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;
import com.halcyonmobile.techinterview.src.networking.model.dto.ResultDTO;
import com.halcyonmobile.techinterview.src.utils.ConnectionError;
import com.halcyonmobile.techinterview.src.utils.FragmentAdapter;
import com.halcyonmobile.techinterview.src.utils.MyPageChangeListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionnaireActivity extends FragmentActivity implements RadioBoxesFragment.ActivityCallbacks, CheckboxesFragment.ActivityCallbacks, TextSimpleFragment.ActivityCallbacks {
    private ViewPager mPager;
    private TextView timerEditText;
    private TextView actualQuestionNumberTextView;
    private Button doneButton;
    private Timer timer;

    private List<Button> unansweredButtonList;
    private List<Integer> elapsedTimeList;
    private List<QuestionCardDTO> cardList;
    private List<String> correctAnswer;


    private int actualTime;
    private int actualQuestion;
    private int allQuestions;

    private static ResultDTO resultDTO;
    private static int actualQuestionNr;
    private static ArrayList<String> resultAnswer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_questionare );
        String selectedPositionId = getIntent().getStringExtra( "selectedPositionId" );
        getQuestionCardList( Integer.parseInt( selectedPositionId ) );

        timerEditText = (TextView) findViewById( R.id.textViewTimer );

        actualQuestionNumberTextView = (TextView) findViewById( R.id.textViewQuestions );

        doneButton = (Button) findViewById( R.id.done );
        doneButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                sendResult( resultDTO );
            }
        } );

        unansweredButtonList = new ArrayList<>();
        elapsedTimeList = new ArrayList<>();
        correctAnswer = new ArrayList<>();
        resultAnswer = new ArrayList<>();
        timer = new Timer();
        resultDTO = new ResultDTO();

        for (int i = 0; i < 40; i++) {
            elapsedTimeList.add( 0 );
            correctAnswer.add( "" );
            resultAnswer.add( " " );
        }

        GridLayout layout = (GridLayout) findViewById( R.id.gridLayout );
        layout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE );
                    imm.hideSoftInputFromWindow( view.getWindowToken(), 0 );
                }
            }
        } );
        LinearLayout.LayoutParams
                params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT );
        params.setMargins( 0, 0, dpToPx( 15 ), dpToPx( 15 ) );
        params.width = dpToPx( 42 );
        params.height = dpToPx( 42 );

        for (int j = 1; j < 40; j++) {
            final Button btn = new Button( getBaseContext() );
            btn.setText( j + "" );
            btn.setId( j + 1 );
            btn.setBackground( getDrawable( R.drawable.button_done ) );
            btn.setLayoutParams( params );
            btn.setTextSize( 12 );
            btn.setTextColor( getResources().getColor( R.color.colorPrimary ) );
            layout.addView( btn );
            btn.setVisibility( Button.INVISIBLE );
            btn.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPager.setCurrentItem( (btn.getId() - 2) );
                }
            } );
            unansweredButtonList.add( btn );
            resultDTO.getResultList().add( new Result() );
        }
        unansweredButtonList.get( 0 ).setVisibility( Button.VISIBLE );

        new CountDownTimer( 1800000, 1000 ) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) ((millisUntilFinished / 1000) % 60);
                int minutes = (int) ((millisUntilFinished / 1000) / 60);
                timerEditText.setText( minutes + ":" + seconds );
                actualTime = elapsedTimeList.get( actualQuestionNr );
                elapsedTimeList.set( (actualQuestionNr), actualTime + 1 );
            }

            public void onFinish() {
                timerEditText.setText( "00:00" );
                sendResult( resultDTO );
            }

        }.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void getQuestionCardList(Integer selectedPositionId) {
        ConnectionImpl connection = new ConnectionImpl();
        connection.getQuestionCardList( new Callback<List<QuestionCardDTO>>() {

            @Override
            public void onResponse(Call<List<QuestionCardDTO>> call, final Response<List<QuestionCardDTO>> response) {
                cardList = response.body();

                int orderNumber = 1;
                for (QuestionCardDTO questionCardDTO : cardList) {
                    questionCardDTO.getQuestion().setId( orderNumber++ );
                }
                List<Fragment> fragmentList = processQuestionCardDTO( cardList );
                FragmentAdapter fragmentAdapter = new FragmentAdapter( getSupportFragmentManager(), fragmentList );

                mPager = (ViewPager) findViewById( R.id.viewpager );
                mPager.setClipToPadding( false );
                mPager.setPageMargin( 48 );
                mPager.setAdapter( fragmentAdapter );

                mPager.setOnTouchListener( new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {

                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE );
                            imm.hideSoftInputFromWindow( view.getWindowToken(), 0 );
                        }
                        return false;
                    }
                } );
                mPager.addOnPageChangeListener( new MyPageChangeListener() {

                    @Override
                    public void onPageSelected(int i) {
                        actualQuestionNumberTextView.setText( (i + 1) + "/" + allQuestions );
                        actualQuestionNr = i;
                        if ((i + 1) == allQuestions) {
                            doneButton.setVisibility( Button.VISIBLE );
                        }
                        if (i < 39) {
                            Button actualButton = unansweredButtonList.get( i );
                            if (resultDTO.getResultList().get( i ).getAnswer() == null) {
                                actualButton.setVisibility( Button.VISIBLE );
                                resultDTO.getResultList().get( i ).setCorrect( false );
                                resultDTO.getResultList().get( i ).setQuestion( cardList.get( i ).getQuestion().getQuestion() );
                                resultDTO.getResultList().get( i ).setDate( Calendar.getInstance().get( Calendar.YEAR ) + "." + (Calendar.getInstance().get( Calendar.MONTH ) + 1) + "." + Calendar.getInstance().get( Calendar.DAY_OF_MONTH ) + " - " + Calendar.getInstance().get( Calendar.HOUR ) + ":" + Calendar.getInstance().get( Calendar.MINUTE ) + ":" + Calendar.getInstance().get( Calendar.SECOND ) );
                                resultDTO.getResultList().get( i ).setIdUser( Integer.parseInt( getIntent().getStringExtra( "userId" ) ) );
                                resultAnswer.set( i, " " );
                            } else {
                                resultAnswer.set( i, resultDTO.getResultList().get( i ).getAnswer() );
                            }
                        }
                        if (resultDTO.getResultList().get( 0 ).getAnswer() == null) {
                            resultDTO.getResultList().get( 0 ).setCorrect( false );
                            resultDTO.getResultList().get( 0 ).setQuestion( cardList.get( 0 ).getQuestion().getQuestion() );
                            resultDTO.getResultList().get( 0 ).setDate( Calendar.getInstance().get( Calendar.YEAR ) + "." + (Calendar.getInstance().get( Calendar.MONTH ) + 1) + "." + Calendar.getInstance().get( Calendar.DAY_OF_MONTH ) + " - " + Calendar.getInstance().get( Calendar.HOUR ) + ":" + Calendar.getInstance().get( Calendar.MINUTE ) + ":" + Calendar.getInstance().get( Calendar.SECOND ) );
                            resultDTO.getResultList().get( 0 ).setIdUser( Integer.parseInt( getIntent().getStringExtra( "userId" ) ) );
                            resultAnswer.set( 0, " " );
                        }
                    }
                } );
            }

            @Override
            public void onFailure(Call<List<QuestionCardDTO>> call, Throwable t) {
                View parentLayout = findViewById( R.id.activity_questionare );
                ConnectionError conectionError = new ConnectionError();
                conectionError.noConnection( parentLayout );
            }
        }, selectedPositionId );
    }

    private void sendResult(ResultDTO resultDTO) {
        int i = 0;
        for (Result result : resultDTO.getResultList()) {
            result.setThinkingTime( elapsedTimeList.get( i++ ) );
        }

        final ConnectionImpl connection = new ConnectionImpl();
        connection.sendUserResult( new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean resp = response.body();
                if (resp) {
                    Intent intent = new Intent( QuestionnaireActivity.this, CongratulationsActivity.class );
                    QuestionnaireActivity.this.startActivity( intent );
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                View parentLayout = findViewById( R.id.activity_questionare );
                ConnectionError conectionError = new ConnectionError();
                conectionError.noConnection( parentLayout );
            }
        }, resultDTO );
    }

    private List<Fragment> processQuestionCardDTO(List<QuestionCardDTO> cardList) {
        List<Fragment> fragmentList = new ArrayList<>();
        allQuestions = cardList.size();
        actualQuestionNumberTextView.setText( "1/" + allQuestions );

        for (QuestionCardDTO card : cardList) {
            switch (card.getQuestionType().getName()) {
                case "checkbox": {
                    String full_answer = "";
                    for (int i = 0; i < card.getAnswers().size(); i++) {
                        if (card.getAnswers().get( i ).getCorrect()) {
                            full_answer = full_answer + card.getAnswers().get( i ).getAnswer() + " -/- ";
                        }
                    }
                    correctAnswer.set( actualQuestion, full_answer );
                    CheckboxesFragment frag = new CheckboxesFragment();
                    Bundle xBundle = new Bundle();
                    xBundle.putSerializable( "data", card );
                    xBundle.putSerializable( "orderNumber", actualQuestion );
                    xBundle.putSerializable( "allQuestionNumber", allQuestions );
                    actualQuestion++;
                    frag.setArguments( xBundle );
                    fragmentList.add( frag );
                    break;
                }
                case "radiobutton": {
                    RadioBoxesFragment frag = new RadioBoxesFragment();
                    Bundle xBundle = new Bundle();
                    xBundle.putSerializable( "data", card );
                    xBundle.putSerializable( "orderNumber", actualQuestion );
                    xBundle.putSerializable( "allQuestionNumber", allQuestions );
                    actualQuestion++;
                    frag.setArguments( xBundle );
                    fragmentList.add( frag );
                    break;
                }
                case "textfield": {
                    TextSimpleFragment frag = new TextSimpleFragment();
                    Bundle xBundle = new Bundle();
                    xBundle.putSerializable( "data", card );
                    xBundle.putSerializable( "orderNumber", actualQuestion );
                    xBundle.putSerializable( "allQuestionNumber", allQuestions );
                    actualQuestion++;
                    frag.setArguments( xBundle );
                    fragmentList.add( frag );
                    break;
                }
            }
        }
        return fragmentList;
    }

    @Override
    public void onQuestionAnswered(Answer answer) {
        unansweredButtonList.get( actualQuestionNr ).setBackgroundResource( R.drawable.answered_button );
        unansweredButtonList.get( actualQuestionNr ).setTextColor( getResources().getColor( R.color.white ) );
        resultDTO.getResultList().get( actualQuestionNr ).setAnswer( answer.getAnswer() );
        resultAnswer.set( actualQuestionNr, answer.getAnswer() );
        resultDTO.getResultList().get( actualQuestionNr ).setCorrect( answer.getCorrect() );
        resultDTO.getResultList().get( actualQuestionNr ).setQuestion( cardList.get( actualQuestionNr ).getQuestion().getQuestion() );
        resultDTO.getResultList().get( actualQuestionNr ).setDate( Calendar.getInstance().get( Calendar.YEAR ) + "." + (Calendar.getInstance().get( Calendar.MONTH ) + 1) + "." + Calendar.getInstance().get( Calendar.DAY_OF_MONTH ) + " - " + Calendar.getInstance().get( Calendar.HOUR ) + ":" + Calendar.getInstance().get( Calendar.MINUTE ) + ":" + Calendar.getInstance().get( Calendar.SECOND ) );
        resultDTO.getResultList().get( actualQuestionNr ).setIdUser( Integer.parseInt( getIntent().getStringExtra( "userId" ) ) );
    }

    @Override
    public void onQuestionCheckBoxAnswered(Answer answer) {
        unansweredButtonList.get( actualQuestionNr ).setBackgroundResource( R.drawable.answered_button );
        unansweredButtonList.get( actualQuestionNr ).setTextColor( getResources().getColor( R.color.white ) );
        resultDTO.getResultList().get( actualQuestionNr ).setAnswer( answer.getAnswer() );
        resultDTO.getResultList().get( actualQuestionNr ).setCorrect( answer.getCorrect() );
        if ((answer.getAnswer()).equals( correctAnswer.get( actualQuestionNr + 1 ) )) {
            resultDTO.getResultList().get( actualQuestionNr ).setCorrect( true );
        } else {
            resultDTO.getResultList().get( actualQuestionNr ).setCorrect( false );
        }
        resultAnswer.set( actualQuestionNr, answer.getAnswer() );
        resultDTO.getResultList().get( actualQuestionNr ).setQuestion( cardList.get( actualQuestionNr ).getQuestion().getQuestion() );
        resultDTO.getResultList().get( actualQuestionNr ).setDate( Calendar.getInstance().get( Calendar.YEAR ) + "." + (Calendar.getInstance().get( Calendar.MONTH ) + 1) + "." + Calendar.getInstance().get( Calendar.DAY_OF_MONTH ) + " - " + Calendar.getInstance().get( Calendar.HOUR ) + ":" + Calendar.getInstance().get( Calendar.MINUTE ) + ":" + Calendar.getInstance().get( Calendar.SECOND ) );
        resultDTO.getResultList().get( actualQuestionNr ).setIdUser( Integer.parseInt( getIntent().getStringExtra( "userId" ) ) );
    }

    @Override
    public void onQuestionFreeTextAnswered(Answer answer) {
        unansweredButtonList.get( actualQuestionNr ).setBackgroundResource( R.drawable.answered_button );
        unansweredButtonList.get( actualQuestionNr ).setTextColor( getResources().getColor( R.color.white ) );
        resultDTO.getResultList().get( actualQuestionNr ).setAnswer( answer.getAnswer() );
        resultDTO.getResultList().get( actualQuestionNr ).setCorrect( answer.getCorrect() );
        resultDTO.getResultList().get( actualQuestionNr ).setQuestion( cardList.get( actualQuestionNr ).getQuestion().getQuestion() );
        resultDTO.getResultList().get( actualQuestionNr ).setDate( Calendar.getInstance().get( Calendar.YEAR ) + "." + (Calendar.getInstance().get( Calendar.MONTH ) + 1) + "." + Calendar.getInstance().get( Calendar.DAY_OF_MONTH ) + " - " + Calendar.getInstance().get( Calendar.HOUR ) + ":" + Calendar.getInstance().get( Calendar.MINUTE ) + ":" + Calendar.getInstance().get( Calendar.SECOND ) );
        resultDTO.getResultList().get( actualQuestionNr ).setIdUser( Integer.parseInt( getIntent().getStringExtra( "userId" ) ) );
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
        return Math.round( dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT) );
    }

    public static void setResultAnswer(int i, String resultAnswer) {
        QuestionnaireActivity.resultAnswer.set( i, resultAnswer );
    }

    public static ArrayList<String> getResultAnswer() {
        return resultAnswer;
    }

}

package com.halcyonmobile.techinterview.src.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.model.Answer;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;

public class RadioBoxesFragment extends Fragment {
    private TextView textViewTitle;
    private RadioGroup radioGroup;
    private ActivityCallbacks activityCallbacks;
    private RadioButton radioButton;

    interface ActivityCallbacks {
        void onQuestionAnswered(Answer answer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate( R.layout.fragment_fragment_radioboxes, container, false );

        textViewTitle = (TextView) rootView.findViewById( R.id.textview_q_title );
        radioGroup = (RadioGroup) rootView.findViewById( R.id.radioGroup );
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        activityCallbacks = (ActivityCallbacks) getActivity();
        final QuestionCardDTO questionCard = (QuestionCardDTO) getArguments().getSerializable( "data" );
        final int orderNumber = (int) getArguments().getSerializable( "orderNumber" );
        textViewTitle.setText( questionCard.getQuestion().getQuestion() );

        for (Answer answer : questionCard.getAnswers()) {
            RadioButton rb = new RadioButton( getActivity() );
            rb.setText( answer.getAnswer() );
            if (answer.getAnswer().equals( QuestionnaireActivity.getResultAnswer().get( orderNumber ) )) {
                rb.setChecked( true );
            }
            rb.setTextSize( TypedValue.COMPLEX_UNIT_SP, 20 );
            rb.setTypeface( Typeface.SANS_SERIF );
            rb.setTextColor( getResources().getColor( R.color.answerTextColor ) );
            rb.setPadding( 24, 12, 0, 12 );
            rb.setLayoutParams( new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT ) );
            radioGroup.addView( rb );
        }

        radioGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if ((i % 4) == 0) {
                    QuestionnaireActivity.setResultAnswer( orderNumber, questionCard.getAnswers().get( 3 ).getAnswer() );
                    activityCallbacks.onQuestionAnswered( questionCard.getAnswers().get( 3 ) );
                    for (int j = 0; j < 4; j++) {
                        radioButton = (RadioButton) radioGroup.getChildAt( j );
                        radioButton.setChecked( false );
                        if (radioButton.getText().equals( QuestionnaireActivity.getResultAnswer().get( orderNumber ) )) {
                            radioButton.setChecked( true );
                        }
                    }
                } else {
                    QuestionnaireActivity.setResultAnswer( orderNumber, questionCard.getAnswers().get( (i % 4) - 1 ).getAnswer() );
                    activityCallbacks.onQuestionAnswered( questionCard.getAnswers().get( (i % 4) - 1 ) );
                    for (int j = 0; j < 4; j++) {
                        radioButton = (RadioButton) radioGroup.getChildAt( j );
                        radioButton.setChecked( false );
                        if (radioButton.getText().equals( QuestionnaireActivity.getResultAnswer().get( orderNumber ) )) {
                            radioButton.setChecked( true );
                        }
                    }
                }
            }
        } );

    }
}

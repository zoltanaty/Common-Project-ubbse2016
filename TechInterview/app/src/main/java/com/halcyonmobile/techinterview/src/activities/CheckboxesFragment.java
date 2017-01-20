package com.halcyonmobile.techinterview.src.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.model.Answer;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;

public class CheckboxesFragment extends Fragment {
    private TextView textViewTitle;
    private LinearLayout layoutCheckBoxes;
    private ActivityCallbacks activityCallbacks;

    interface ActivityCallbacks {
        void onQuestionCheckBoxAnswered(Answer answer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate( R.layout.fragment_fragment_checkboxes, container, false );
        textViewTitle = (TextView) rootView.findViewById( R.id.textview_q_title );
        layoutCheckBoxes = (LinearLayout) rootView.findViewById( R.id.linearLayout_checkboxes );
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        activityCallbacks = (ActivityCallbacks) getActivity();
        final QuestionCardDTO questionCard = (QuestionCardDTO) getArguments().getSerializable( "data" );
        final int orderNumber = (int) getArguments().getSerializable( "orderNumber" );
        textViewTitle.setText( questionCard.getQuestion().getQuestion() );
        final Answer answerToSend = new Answer();
        for (final Answer answer : questionCard.getAnswers()) {
            CheckBox cb = new CheckBox( getActivity() );
            cb.setText( answer.getAnswer() );
            if ((QuestionnaireActivity.getResultAnswer().get( orderNumber )).contains( answer.getAnswer() )) {
                cb.setChecked( true );
            }
            cb.setTextSize( TypedValue.COMPLEX_UNIT_SP, 20 );
            cb.setTypeface( Typeface.SANS_SERIF );
            cb.setTextColor( getResources().getColor( R.color.answerTextColor ) );
            cb.setPadding( 24, 12, 0, 12 );
            cb.setLayoutParams( new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT ) );
            cb.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    answerToSend.setAnswer( "" );
                    for (Integer i = 0; i < layoutCheckBoxes.getChildCount(); i++) {
                        CheckBox checkBox = (CheckBox) layoutCheckBoxes.getChildAt( i );
                        if (checkBox.isChecked()) {
                            answerToSend.setAnswer( answerToSend.getAnswer() + checkBox.getText() + " -/- " );
                        }
                    }
                    QuestionnaireActivity.setResultAnswer( orderNumber, answerToSend.toString() );
                    activityCallbacks.onQuestionCheckBoxAnswered( answerToSend );
                }
            } );
            layoutCheckBoxes.addView( cb );
        }
    }
}


package com.halcyonmobile.techinterview.src.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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


public class FragmentRadioboxes extends Fragment {
    private FragmentActivity mContext;
    private TextView textViewTitle;
    private RadioGroup radioGroup;
    private ActivityCallbacks activityCallbacks;

    interface ActivityCallbacks {
        void onQuestionAnswered(Answer answer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fragment_radioboxes, container, false);

        textViewTitle = (TextView) rootView.findViewById(R.id.textview_q_title);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        final QuestionCardDTO questionCard = (QuestionCardDTO) getArguments().getSerializable("data");

        textViewTitle.setText("#" + +questionCard.getQuestion().getId() + " " + questionCard.getQuestion().getQuestion());
        for (Answer answer : questionCard.getAnswers()) {
            RadioButton rb = new RadioButton(mContext);
            rb.setText(answer.getAnswer());
            rb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            rb.setTypeface(Typeface.SANS_SERIF);
            rb.setTextColor(getResources().getColor(R.color.answerTextColor));
            rb.setPadding(24, 12, 0, 12);
            rb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioGroup.addView(rb);
        }

        // Updating the activity on answer selected
        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                activityCallbacks.onQuestionAnswered(questionCard.getAnswers().get(i));
            }
        });*/
    }
}

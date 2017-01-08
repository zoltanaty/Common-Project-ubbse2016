package com.halcyonmobile.techinterview.src.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.model.Answer;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;

import java.util.ArrayList;

//TODO CR: Most of the TODO-s from the CheckboxesFragment class applies here as well. You should consider creating a common parent for these Fragments to avoid code duplication.
//TODO CR: The communication with the parent Activity for instance should be abstract enough for both classes to use it in the same way. [Peter]
public class RadioBoxesFragment extends Fragment {
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
        activityCallbacks = (ActivityCallbacks) getActivity();

        final QuestionCardDTO questionCard = (QuestionCardDTO) getArguments().getSerializable("data");
        textViewTitle.setText(questionCard.getQuestion().getQuestion());

        for (Answer answer : questionCard.getAnswers()) {
            RadioButton rb = new RadioButton(getActivity());
            rb.setText(answer.getAnswer());
            rb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            rb.setTypeface(Typeface.SANS_SERIF);
            rb.setTextColor(getResources().getColor(R.color.answerTextColor));
            rb.setPadding(24, 12, 0, 12);
            rb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioGroup.addView(rb);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //[zmate] to [Peter] - This is necessary. It can't be solved other way, because all of our radio buttons are in a single RadioGroup
                if ((i % 4) == 0) {
                    activityCallbacks.onQuestionAnswered(questionCard.getAnswers().get(3));
                } else {
                    activityCallbacks.onQuestionAnswered(questionCard.getAnswers().get((i % 4) - 1));
                }
            }
        });
    }
}

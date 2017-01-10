package com.halcyonmobile.techinterview.src.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.model.Answer;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;

public class CheckboxesFragment extends Fragment {
    private TextView textViewTitle;
    private LinearLayout layoutCheckBoxes;
    private ActivityCallbacks activityCallbacks;
    private CardView cardView;

    interface ActivityCallbacks {
        void onQuestionCheckBoxAnswered(Answer answer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fragment_checkboxes, container, false);
        //TODO CR: Consider moving these to the onViewCreated() method, onCreateView() should only be responsible for inflating the layout. [Peter]
        textViewTitle = (TextView) rootView.findViewById(R.id.textview_q_title);
        layoutCheckBoxes = (LinearLayout) rootView.findViewById(R.id.linearLayout_checkboxes);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //TODO CR: Replace the Serializable implementation with Parcelable, it's much more optimal for Android. [Peter]
        activityCallbacks = (ActivityCallbacks) getActivity();
        final QuestionCardDTO questionCard = (QuestionCardDTO) getArguments().getSerializable("data");
        textViewTitle.setText(questionCard.getQuestion().getQuestion());
        final Answer answerToSend = new Answer();

        for (final Answer answer : questionCard.getAnswers()) {
            CheckBox cb = new CheckBox(getActivity());
            cb.setText(answer.getAnswer());
            cb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            cb.setTypeface(Typeface.SANS_SERIF);
            //TODO CR: Don't use deprecated methods. ContextCompat.getColor() is what you need in this case. [Peter]
            cb.setTextColor(getResources().getColor(R.color.answerTextColor));
            cb.setPadding(24, 12, 0, 12);
            cb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    answerToSend.setAnswer("");
                    for (Integer i = 0; i < layoutCheckBoxes.getChildCount(); i++) {
                        CheckBox checkBox = (CheckBox) layoutCheckBoxes.getChildAt(i);
                        if (checkBox.isChecked()) {
                            answerToSend.setAnswer(answerToSend.getAnswer() + checkBox.getText() + " -/- ");
                        }
                    }
                    activityCallbacks.onQuestionCheckBoxAnswered(answerToSend);
                }
            });
            layoutCheckBoxes.addView(cb);
        }
    }
}


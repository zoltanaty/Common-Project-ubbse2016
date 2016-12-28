package com.halcyonmobile.techinterview.src.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.model.Answer;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;


public class FragmentCheckboxes extends Fragment {
    private FragmentActivity mContext;
    private TextView textViewTitle;
    private LinearLayout layoutCheckBoxes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_fragment_checkboxes,container,false);

        textViewTitle = (TextView) rootView.findViewById(R.id.textview_q_title);
        layoutCheckBoxes=(LinearLayout) rootView.findViewById(R.id.linearLayout_checkboxes);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        QuestionCardDTO questionCard = (QuestionCardDTO) getArguments().getSerializable("data");

        textViewTitle.setText(questionCard.getQuestion().getQuestion());
        for (Answer answer : questionCard.getAnswers()){
            CheckBox cb = new CheckBox(mContext);
            cb.setText(answer.getAnswer());
            cb.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            cb.setTypeface(Typeface.SANS_SERIF);
            cb.setTextColor(getResources().getColor(R.color.answerTextColor));
            cb.setPadding(24,12,0,12);
            cb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutCheckBoxes.addView(cb);
            //radioButtons.add(rb);*/
        }
    }
}

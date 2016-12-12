package com.halcyonmobile.techinterview.src.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.model.Answer;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;

import java.util.ArrayList;


public class FragmentRadioboxes extends Fragment {
    private FragmentActivity mContext;
    private TextView textViewTitle;
    private RadioGroup radioGroup;
    //private final ArrayList<RadioButton> radioButtons = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_fragment_radioboxes,container,false);

        textViewTitle = (TextView) rootView.findViewById(R.id.textview_q_title);
        radioGroup=(RadioGroup) rootView.findViewById(R.id.radioGroup);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        QuestionCardDTO questionCard = (QuestionCardDTO) getArguments().getSerializable("data");

        textViewTitle.setText(questionCard.getQuestion().getQuestion());
        for (Answer answer : questionCard.getAnswers()){
            RadioButton rb = new RadioButton(mContext);
            rb.setText(answer.getAnswer());
            rb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioGroup.addView(rb);
            //radioButtons.add(rb);*/
        }
    }
}

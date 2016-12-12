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
import android.widget.TextView;

import com.halcyonmobile.techinterview.R;
import com.halcyonmobile.techinterview.src.networking.model.dto.QuestionCardDTO;


public class FragmentTextSimple extends Fragment {
    private FragmentActivity mContext;
    private TextView textViewTitle;
    private EditText editTextAnswer;
    private TextView questionviewText;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_fragment_text_simple,container,false);
        ViewGroup mainView = (ViewGroup)inflater.inflate(R.layout.activity_questionare,container,false);

        textViewTitle = (TextView) rootView.findViewById(R.id.textview_q_title);
        editTextAnswer=(EditText) rootView.findViewById(R.id.editText_answer);
        questionviewText=(TextView) mainView.findViewById(R.id.textViewQuestions);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        QuestionCardDTO questionCard = (QuestionCardDTO) getArguments().getSerializable("data");
        //questionviewText.setText(questionCard.getQuestion().getId());

        textViewTitle.setText("#" + + questionCard.getQuestion().getId()+ " " + questionCard.getQuestion().getQuestion());

    }
}

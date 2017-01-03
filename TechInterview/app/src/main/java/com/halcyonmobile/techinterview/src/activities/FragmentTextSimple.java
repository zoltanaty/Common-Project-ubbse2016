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
    //TODO CR: Remove unused variables. [Peter]
    private FragmentActivity mContext;
    private TextView textViewTitle;
    private EditText editTextAnswer;
    private TextView questionviewText;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_fragment_text_simple,container,false);

        textViewTitle = (TextView) rootView.findViewById(R.id.textview_q_title);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        QuestionCardDTO questionCard = (QuestionCardDTO) getArguments().getSerializable("data");
    System.out.println(questionCard);
        textViewTitle.setText(questionCard.getQuestion().getQuestion());

    }
}

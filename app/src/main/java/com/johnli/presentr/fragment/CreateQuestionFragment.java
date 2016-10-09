package com.johnli.presentr.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.johnli.presentr.R;
import com.johnli.presentr.model.QuestionPost;
import com.johnli.presentr.presenter.CreateQuestionPresenter;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateQuestionFragment extends Fragment {

    public static final String TAG = "CreateQuestionFragment";
    public static final String ROOM_ID_ARG_KEY = "roomId";
    public static final String USER_ID_ARG_KEY = "userId";
    EditText questionEditText;
    Button postButton;

    CreateQuestionPresenter presenter;
    String roomId;
    String userId;

    public CreateQuestionFragment() {
        // Required empty public constructor
    }

    public static CreateQuestionFragment newInstance() {
        CreateQuestionFragment fragment = new CreateQuestionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CreateQuestionPresenter();
        Bundle bundle = savedInstanceState;
        if(bundle == null) {
            bundle = getArguments();
        }

        if(bundle != null) {
            roomId = bundle.getString(ROOM_ID_ARG_KEY);
            userId = bundle.getString(USER_ID_ARG_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_question, container, false);
        bindUi(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.attach(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.detach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ROOM_ID_ARG_KEY, roomId);
        outState.putString(USER_ID_ARG_KEY, userId);
    }

    void bindUi(View view) {
        questionEditText = (EditText) view.findViewById(R.id.question_edit_text);
        postButton = (Button) view.findViewById(R.id.create_question_btn);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createPost(userId, roomId, questionEditText.getText().toString());
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}

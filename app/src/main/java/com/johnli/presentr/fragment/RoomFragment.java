package com.johnli.presentr.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.johnli.presentr.PostListAdapter;
import com.johnli.presentr.R;
import com.johnli.presentr.model.QuestionPost;
import com.johnli.presentr.model.provider.FQuestionPostProvider;
import com.johnli.presentr.view.RoomView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class RoomFragment extends Fragment implements RoomView, PostListAdapter.QuestionPostSelectedListener, FQuestionPostProvider.RoomProviderDelegate {

    List<QuestionPost> postList;

    RecyclerView recyclerView;
    PostListAdapter postListAdapter;
    FQuestionPostProvider provider;
    private String roomId;

    public RoomFragment() { }

    public static RoomFragment newInstance() {
        return new RoomFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        bindUi(view);
        provider = new FQuestionPostProvider(this);

        Bundle bundle = savedInstanceState;
        if (bundle == null) {
            bundle = getArguments();
        }
        roomId = bundle.getString("roomId");
        provider.getPostsWithRoomId(roomId);

        return view;
    }

    void bindUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        postListAdapter = new PostListAdapter(this, postList);
        recyclerView.setAdapter(postListAdapter);
    }

    @Override
    public void onSelect(QuestionPost post) {

    }

    @Override
    public void onVote(QuestionPost post) {

    }

    @Override
    public void publish(Map<String, QuestionPost> data) {
        postList = new ArrayList<>(data.values());
        postListAdapter.setData(postList);
        postListAdapter.notifyDataSetChanged();
    }

    @Override
    public void createQuestionPressed() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        CreateQuestionFragment createQuestionFragment = CreateQuestionFragment.newInstance();

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid(); //HACK & REFACTOR
        Bundle bundle = new Bundle();
        bundle.putString(CreateQuestionFragment.ROOM_ID_ARG_KEY, roomId);
        bundle.putString(CreateQuestionFragment.USER_ID_ARG_KEY, userId);
        createQuestionFragment.setArguments(bundle);

        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, createQuestionFragment, CreateQuestionFragment.TAG);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

package com.johnli.presentr.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnli.presentr.PostListAdapter;
import com.johnli.presentr.R;
import com.johnli.presentr.model.QuestionPost;
import com.johnli.presentr.model.provider.QuestionPostProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A placeholder fragment containing a simple view.
 */
public class RoomFragment extends Fragment implements PostListAdapter.QuestionPostSelectedListener, QuestionPostProvider.RoomProviderDelegate {

    List<QuestionPost> postList;

    RecyclerView recyclerView;
    PostListAdapter postListAdapter;
    QuestionPostProvider provider;

    public RoomFragment() {
    }

    public static RoomFragment newInstance() {
        return new RoomFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room, container, false);
        bindUi(view);
        provider = new QuestionPostProvider(this);

        Bundle bundle = savedInstanceState;
        if(bundle == null) {
            bundle = getArguments();
        }
        String roomId = bundle.getString("roomId");
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
}

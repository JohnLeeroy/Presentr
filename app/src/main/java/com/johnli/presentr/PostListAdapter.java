package com.johnli.presentr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnli.presentr.model.QuestionPost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnli on 9/29/16.
 */
public class PostListAdapter extends RecyclerView.Adapter<QuestionPostViewHolder>  {

    List<QuestionPost> dataSet = new ArrayList<>();
    QuestionPostSelectedListener listener;

    public PostListAdapter(QuestionPostSelectedListener listener, List<QuestionPost> data) {
        this.listener = listener;
        dataSet = data;
    }

    @Override
    public QuestionPostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.title_view_holder, parent, false);
        QuestionPostViewHolder channelViewHolder = new QuestionPostViewHolder(v, listener);
        return channelViewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionPostViewHolder holder, int position) {
        QuestionPost post = dataSet.get(position);
        holder.setPost(post);
    }

    @Override
    public int getItemCount() {
        if(dataSet == null) {
            return 0;
        }
        return dataSet.size();
    }

    public void setData(List<QuestionPost> postList) {
        dataSet = postList;
    }

    public interface QuestionPostSelectedListener {
        void onSelect(QuestionPost post);
        void onVote(QuestionPost post);
    }
}

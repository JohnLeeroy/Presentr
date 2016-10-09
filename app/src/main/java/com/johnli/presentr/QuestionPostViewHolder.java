package com.johnli.presentr;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.johnli.presentr.model.QuestionPost;

/**
 * Created by johnli on 9/29/16.
 */
public class QuestionPostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    QuestionPost post;

    TextView titleLabel;

    PostListAdapter.QuestionPostSelectedListener listener;

    public QuestionPostViewHolder(View rootView, PostListAdapter.QuestionPostSelectedListener listener) {
        super(rootView);
        bindUI();
        this.listener = listener;
    }

    void bindUI() {
        titleLabel = (TextView) itemView.findViewById(R.id.title);
        itemView.setOnClickListener(this);
    }

    public void setPost(QuestionPost post) {
        this.post = post;
        titleLabel.setText(post.getTitle());
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onSelect(post);
        }
    }
}
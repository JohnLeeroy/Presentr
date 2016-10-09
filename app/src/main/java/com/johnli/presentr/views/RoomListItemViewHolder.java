package com.johnli.presentr.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.johnli.presentr.R;
import com.johnli.presentr.model.Room;

/**
 * Created by johnli on 9/29/16.
 */
public class RoomListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    Room room;

    TextView titleLabel;

    RoomListAdapter.RoomSelectedListener listener;

    public RoomListItemViewHolder(View rootView, RoomListAdapter.RoomSelectedListener listener) {
        super(rootView);
        bindUI();
        this.listener = listener;
    }

    void bindUI() {
        titleLabel = (TextView) itemView.findViewById(R.id.title);
        itemView.setOnClickListener(this);
    }

    public void setRoom(Room room) {
        this.room = room;
        titleLabel.setText(room.getTitle());
    }

    @Override
    public void onClick(View v) {
        if(listener != null) {
            listener.onSelect(room);
        }
    }
}

package com.johnli.presentr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnli.presentr.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnli on 9/29/16.
 */
public class RoomListAdapter extends RecyclerView.Adapter<RoomListItemViewHolder>  {

    List<Room> dataSet = new ArrayList<>();
    RoomSelectedListener listener;

    public RoomListAdapter(RoomSelectedListener listener, List<Room> data) {
        this.listener = listener;
        dataSet = data;
    }

    @Override
    public RoomListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.title_view_holder, parent, false);
        RoomListItemViewHolder channelViewHolder = new RoomListItemViewHolder(v, listener);
        return channelViewHolder;
    }

    @Override
    public void onBindViewHolder(RoomListItemViewHolder holder, int position) {
        Room channel = dataSet.get(position);
        holder.setRoom(channel);
    }

    public void setData(List<Room> rooms) {
        dataSet = rooms;
    }

    @Override
    public int getItemCount() {
        if(dataSet == null) {
            return 0;
        }
        return dataSet.size();
    }

    public interface RoomSelectedListener {
        void onSelect(Room room);
    }
}

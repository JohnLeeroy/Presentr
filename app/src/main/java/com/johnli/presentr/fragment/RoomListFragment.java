package com.johnli.presentr.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnli.presentr.R;
import com.johnli.presentr.RoomListAdapter;
import com.johnli.presentr.activity.RoomActivity;
import com.johnli.presentr.model.Room;
import com.johnli.presentr.model.provider.RoomListProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class RoomListFragment extends Fragment implements RoomListAdapter.RoomSelectedListener, RoomListProvider.RoomListProviderDelegate {

    List<Room> roomList;
    RoomListProvider provider;

    RecyclerView recyclerView;
    RoomListAdapter roomListAdapter;


    public RoomListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RoomListFragment newInstance() {
        RoomListFragment fragment = new RoomListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        provider = new RoomListProvider(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room_list, container, false);
        bindUi(view);
        return view;
    }

    void bindUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        roomListAdapter = new RoomListAdapter(this, roomList);
        recyclerView.setAdapter(roomListAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSelect(Room room) {
        Intent intent = new Intent(getActivity(), RoomActivity.class);
        startActivity(intent);
    }

    @Override
    public void publish(Map<String, Room> data) {
        roomList = new ArrayList<Room>(data.values());
        roomListAdapter.setData(roomList);
        roomListAdapter.notifyDataSetChanged();
    }
}

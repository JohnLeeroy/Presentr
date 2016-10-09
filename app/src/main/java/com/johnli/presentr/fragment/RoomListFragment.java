package com.johnli.presentr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johnli.presentr.R;
import com.johnli.presentr.presenter.RoomListPresenter;
import com.johnli.presentr.view.RoomListView;
import com.johnli.presentr.views.RoomListAdapter;
import com.johnli.presentr.activity.RoomActivity;
import com.johnli.presentr.fragment.dialog.EditTextDialogFragment;
import com.johnli.presentr.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RoomListFragment extends Fragment implements RoomListAdapter.RoomSelectedListener, RoomListView {

    List<Room> roomList;

    RecyclerView recyclerView;
    RoomListAdapter roomListAdapter;

    EditTextDialogFragment editTextDialogFragment;
    private String CREATE_ROOM_DIALOG_TAG = "CREATE_ROOM_DIALOG_TAG";

    RoomListPresenter presenter;

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
        presenter = new RoomListPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_list, container, false);
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

    void bindUi(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateRoomDialogFragment();
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        roomListAdapter = new RoomListAdapter(this, roomList);
        recyclerView.setAdapter(roomListAdapter);
    }

    void showCreateRoomDialogFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        editTextDialogFragment = (EditTextDialogFragment) fragmentManager.findFragmentByTag(CREATE_ROOM_DIALOG_TAG);
        if (editTextDialogFragment == null) {
            editTextDialogFragment = EditTextDialogFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString(EditTextDialogFragment.TITLE_KEY, "Create Room");
            editTextDialogFragment.setArguments(bundle);
        } else {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(editTextDialogFragment);
            transaction.commit();
        }
        editTextDialogFragment.show(fragmentManager, CREATE_ROOM_DIALOG_TAG);
    }

    void dismissCreateRoomDialogFragment() {
        if(editTextDialogFragment != null) {
            editTextDialogFragment.dismiss();
        }
    }

    public void createRoom(String roomName) {
        presenter.createRoom(roomName);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSelect(Room room) {
        Intent intent = new Intent(getActivity(), RoomActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("roomId", room.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void updateRoomList(Map<String, Room> data) {
        roomList = new ArrayList<Room>(data.values());
        roomListAdapter.setData(roomList);
        roomListAdapter.notifyDataSetChanged();
    }
}

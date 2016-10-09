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

import com.google.firebase.auth.FirebaseAuth;
import com.johnli.presentr.R;
import com.johnli.presentr.RoomListAdapter;
import com.johnli.presentr.activity.RoomActivity;
import com.johnli.presentr.fragment.dialog.EditTextDialogFragment;
import com.johnli.presentr.fragment.dialog.ProgressDialogFragment;
import com.johnli.presentr.model.Room;
import com.johnli.presentr.model.provider.FRoomListProvider;
import com.johnli.presentr.network.CreateRoomRequest;
import com.johnli.presentr.network.FirebaseRequestManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class RoomListFragment extends Fragment implements RoomListAdapter.RoomSelectedListener, FRoomListProvider.RoomListProviderDelegate {

    List<Room> roomList;
    FRoomListProvider provider;

    RecyclerView recyclerView;
    RoomListAdapter roomListAdapter;

    EditTextDialogFragment editTextDialogFragment;
    private String CREATE_ROOM_DIALOG_TAG = "CREATE_ROOM_DIALOG_TAG";

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
        provider = new FRoomListProvider(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_list, container, false);
        bindUi(view);
        return view;
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
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid(); //HACK & REFACTOR

        Room room = new Room();
        room.setId(UUID.randomUUID().toString());
        room.setTitle(roomName);
        room.setCreatorId(userId);
        room.setTimestamp(System.currentTimeMillis());
        CreateRoomRequest request = new CreateRoomRequest(room);
        FirebaseRequestManager.getInstance().sendRequest(request);
        dismissCreateRoomDialogFragment();
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
    public void publish(Map<String, Room> data) {
        roomList = new ArrayList<Room>(data.values());
        roomListAdapter.setData(roomList);
        roomListAdapter.notifyDataSetChanged();
    }
}

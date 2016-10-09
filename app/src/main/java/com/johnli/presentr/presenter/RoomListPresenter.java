package com.johnli.presentr.presenter;

import com.google.firebase.auth.FirebaseAuth;
import com.johnli.presentr.model.Room;
import com.johnli.presentr.model.provider.FRoomListProvider;
import com.johnli.presentr.network.CreateRoomRequest;
import com.johnli.presentr.network.FirebaseRequestManager;
import com.johnli.presentr.view.RoomListView;

import java.util.Map;
import java.util.UUID;

/**
 * Created by johnli on 10/9/16.
 */
public class RoomListPresenter implements FRoomListProvider.RoomListProviderDelegate{

    RoomListView view;
    FRoomListProvider provider;

    public RoomListPresenter() {
        provider = new FRoomListProvider(this);
    }

    public void attach(RoomListView view) {
        this.view = view;
    }

    public void detach() {
        view = null;
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
        //dismissCreateRoomDialogFragment();
    }


    @Override
    public void publish(Map<String, Room> data) {
        if(view != null) {
            view.updateRoomList(data);
        }
    }
}

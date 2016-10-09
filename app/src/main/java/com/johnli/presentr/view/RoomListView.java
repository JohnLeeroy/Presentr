package com.johnli.presentr.view;

import com.johnli.presentr.model.Room;

import java.util.Map;

/**
 * Created by johnli on 10/9/16.
 */
public interface RoomListView {

    void updateRoomList(Map<String, Room> data);
}

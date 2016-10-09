package com.johnli.presentr.network;

import com.johnli.presentr.model.QuestionPost;
import com.johnli.presentr.model.Room;

/**
 * Created by johnli on 10/9/16.
 */
public class CreateRoomRequest implements FirebaseRequest<Room> {

    Room room;

    public CreateRoomRequest(Room room) {
        this.room = room;
    }

    @Override
    public String getPath() {
        return "/rooms/" + room.getId();
    }

    @Override
    public Room getData() {
        return room;
    }

}

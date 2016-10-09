package com.johnli.presentr.model.provider;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.johnli.presentr.model.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by johnli on 10/8/16.
 */
public class RoomListProvider implements ChildEventListener {
    private DatabaseReference mDatabase;
    private String TAG = "ADFJGSDKHG";

    private Map<String, Room> rooms;
    RoomListProviderDelegate delegate;

    public RoomListProvider(@NonNull RoomListProviderDelegate delegate) {
        this.delegate = delegate;
        rooms = new HashMap<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference roomRef = mDatabase.child("rooms");
        roomRef.addChildEventListener(this);
    }

    private void updateRoom(DataSnapshot snapshot) {
        Room room = snapshot.getValue(Room.class);
        room.setId(snapshot.getKey());
        rooms.put(room.getId(), room);
        delegate.publish(rooms);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        updateRoom(dataSnapshot);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        updateRoom(dataSnapshot);
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        String key = dataSnapshot.getKey();
        rooms.remove(key);
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        //DNI
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public interface RoomListProviderDelegate {
        //TODO refactor & optimize
        void publish(Map<String, Room> data);
    }
}

package com.johnli.presentr.model.provider;

import android.support.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.johnli.presentr.model.QuestionPost;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by johnli on 9/29/16.
 */
public class FQuestionPostProvider implements ChildEventListener {
    private DatabaseReference mDatabase;
    private String TAG = "akjknfkljsfgklli";

    private Map<String, QuestionPost> posts;
    RoomProviderDelegate delegate;

    public FQuestionPostProvider(@NonNull RoomProviderDelegate delegate) {
        this.delegate = delegate;
        posts = new HashMap<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    
    public void getPostsWithRoomId(String id) {
        Query roomRef = mDatabase.child("questions").child(id);
        roomRef.addChildEventListener(this);
    }

    private void updateRoom(DataSnapshot snapshot) {
        QuestionPost questionPost = snapshot.getValue(QuestionPost.class);
        questionPost.setId(snapshot.getKey());
        questionPost.setRoomId(snapshot.getRef().getParent().getKey());
        posts.put(questionPost.getId(), questionPost);
        delegate.publish(posts);
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
        posts.remove(key);
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        //DNI
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public interface RoomProviderDelegate {
        //TODO refactor & optimize
        void publish(Map<String, QuestionPost> data);
    }
}

package com.johnli.presentr.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by johnli on 9/29/16.
 */
@IgnoreExtraProperties
public class QuestionPost{

    private String id;
    private String roomId;

    private String posterId;
    private String title;
    private int upVotes;

    public QuestionPost() { }

    public QuestionPost(String posterId, String title) {
        this.posterId = posterId;
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public String getId() { return id; }

    @Exclude
    public String getRoomId() { return roomId; }

    public String getTitle() { return title; }

    public String getPosterId() { return posterId; }

    public int getUpVotes() { return upVotes; }

    public void setRoomId(String roomId) { this.roomId = roomId; }

    public void setPosterId(String posterId) { this.posterId = posterId; }

    public void setTitle(String title) { this.title = title; }

    public void setUpVotes(int upVotes) { this.upVotes = upVotes; }
}

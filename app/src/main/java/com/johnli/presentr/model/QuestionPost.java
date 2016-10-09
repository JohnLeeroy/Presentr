package com.johnli.presentr.model;

/**
 * Created by johnli on 9/29/16.
 */
public class QuestionPost implements PostInterface {

    private String id;
    private String roomId;
    public String posterId;
    public String title;
    public int upvotes;

    public QuestionPost() { }

    public QuestionPost(String posterId, String title) {
        this.posterId = posterId;
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPosterId() {
        return posterId;
    }

    @Override
    public int getVoteCount() {
        return upvotes;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}

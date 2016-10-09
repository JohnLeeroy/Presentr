package com.johnli.presentr.model;

import com.google.firebase.database.Exclude;

/**
 * Created by johnli on 9/29/16.
 */
public class Room {

    private String id;
    private String title;
    private String creatorId;
    private long timestamp;

    public Room() {}

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

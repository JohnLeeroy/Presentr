package com.johnli.presentr.model;

/**
 * Created by johnli on 9/29/16.
 */
public class Room {

    private String id;
    public String title;
    public String creatorId;
    public long timestamp;

    public Room() {}

    public Room(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return title;
    }

    public String getId() {
        return id;
    }
}

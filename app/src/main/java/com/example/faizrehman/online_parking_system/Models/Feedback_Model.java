package com.example.faizrehman.online_parking_system.Models;

/**
 * Created by faiz on 1/28/2017.
 */

public class Feedback_Model {
    private String uid;
    private String message;

    public Feedback_Model(String uid, String message) {
        this.uid = uid;
        this.message = message;
    }

    public Feedback_Model() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.example.faizrehman.online_parking_system.Models;

/**
 * Created by faizrehman on 1/27/17.
 */

public class UserModels {
    private String email;
    private String password;
    private String cpassword;
    private String userID;
    private String fname;
    private String lname;

    public UserModels() {
    }

    public UserModels(String email, String password, String cpassword, String userID, String fname, String lname) {
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
        this.userID = userID;
        this.fname = fname;
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}

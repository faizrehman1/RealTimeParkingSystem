package com.example.faizrehman.online_parking_system.Models;

/**
 * Created by faizrehman on 1/27/17.
 */

public class Slot_ReservationModel {

    private String selectdate;
    private int starttime;
    private int selectHour;
    private String currentTime;
    private String plazaName;
    private int slot_no;
    private boolean isBooked;
    private String custName;
    private String custID;

    public Slot_ReservationModel(int slot_no, boolean isBooked) {
        this.slot_no = slot_no;
        this.isBooked = isBooked;

    }

    public Slot_ReservationModel(String selectdate, int starttime, int selectHour, String currentTime, String plazaName, int slot_no, boolean isBooked, String custName, String custID) {
        this.selectdate = selectdate;
        this.starttime = starttime;
        this.selectHour = selectHour;
        this.currentTime = currentTime;
        this.plazaName = plazaName;
        this.slot_no = slot_no;
        this.isBooked = isBooked;
        this.custName = custName;
        this.custID = custID;
    }

    public Slot_ReservationModel() {
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String getSelectdate() {
        return selectdate;
    }

    public void setSelectdate(String selectdate) {
        this.selectdate = selectdate;
    }

    public int getStarttime() {
        return starttime;
    }

    public void setStarttime(int starttime) {
        this.starttime = starttime;
    }

    public int getSelectHour() {
        return selectHour;
    }

    public void setSelectHour(int selectHour) {
        this.selectHour = selectHour;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getPlazaName() {
        return plazaName;
    }

    public void setPlazaName(String plazaName) {
        this.plazaName = plazaName;
    }

    public int getSlot_no() {
        return slot_no;
    }

    public void setSlot_no(int slot_no) {
        this.slot_no = slot_no;
    }
}

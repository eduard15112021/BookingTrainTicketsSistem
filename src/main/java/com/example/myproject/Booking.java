package com.example.myproject;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Booking {
    private String username;
    public String title;
    private String date;
    private String time;
    private String seats;
    private String payed;



    public Booking(String username, String title, String date, String time, String seats, String payed) {
        this.username = username;
        this.title = title;
        this.date = date;
        this.time = time;
        this.seats = seats;
        this.payed = payed;

    }

    public Booking() {
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {this.date = date;}

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getSeats() {
        return seats;
    }
    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getPayed() {
        return payed;
    }
    public void setPayed(String Payed) {
        this.payed = payed;
    }


}




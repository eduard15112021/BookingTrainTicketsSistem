package com.example.myproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class BookingItem extends Booking{

    private final StringProperty username;
    private final StringProperty title;
    private final StringProperty date;
    private final StringProperty time;
    private final StringProperty seats;
    private final StringProperty payed;
    public BookingItem(){

        username = new SimpleStringProperty(this ,"username");
        title = new SimpleStringProperty(this ,"title");
        date = new SimpleStringProperty(this ,"date");
        time = new SimpleStringProperty(this ,"time");
        seats = new SimpleStringProperty(this ,"seats");
        payed = new SimpleStringProperty(this ,"payed");
    }



   public StringProperty usernameProperty(){return username;}
    public String getUsername1(){return username.get();}
    public void setUsername1(String newUsername){username.set(newUsername);}

    public StringProperty titleProperty(){return title;}
    public String getTitle1(){return title.get();}
    public void setTitle1(String newTitle){title.set(newTitle);}

    public StringProperty dateProperty(){return date;}
    public String getDate1(){return date.get();}
    public void setDate1(String newDate){date.set(newDate);}

    public StringProperty timeProperty(){return time;}
    public String getTime1(){return time.get();}
    public void setTime1(String newTime){time.set(newTime);}

    public StringProperty seatsProperty(){return seats;}
    public String getSeats1(){return seats.get();}
    public void setSeats1(String newSeats){seats.set(newSeats);}

    public StringProperty payedProperty(){return payed;}
    public String getPayed1(){return payed.get();}
    public void setPayed1(String newPayed){payed.set(newPayed);}



@Override
    public String toString(){
        return String.format("%s[username=%s,title=%s,date=%s,time=%s,seats=%s,payed=%s]",
                getClass().getName(), getUsername1(),getTitle1(),getDate1(),getTime1(),getSeats1(),getPayed1()
                );
}



}


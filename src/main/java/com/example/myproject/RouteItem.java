package com.example.myproject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class RouteItem extends Route {

    private final StringProperty route;
    private final StringProperty from;
    private final StringProperty to;
    private final StringProperty time1;
    private final StringProperty time2;
    private final StringProperty fry ;
    private final StringProperty price;
    public RouteItem(){

        route = new SimpleStringProperty(this ,"title");
       from = new SimpleStringProperty(this ,"dateFrom");
        to = new SimpleStringProperty(this ,"dateTo");
        time1 = new SimpleStringProperty(this ,"time1");
        time2 = new SimpleStringProperty(this ,"time2");
        fry = new SimpleStringProperty(this ,"fryplace");
        price = new SimpleStringProperty(this ,"price");
    }


    public StringProperty routeProperty(){return route;}
    public String getRoute1(){return route.get();}
    public void setRoute1(String newRoute){route.set(newRoute);}

    public StringProperty fromProperty(){return from;}
    public String getFrom1(){return from.get();}
    public void setFrom1(String newFrom){from.set(newFrom);}

    public StringProperty toProperty(){return to;}
    public String getTo1(){return to.get();}
    public void setTo1(String newTo){to.set(newTo);}

    public StringProperty time1Property(){return time1;}
    public String getTime11(){return time1.get();}
    public void setTime11(String newTime1){time1.set(newTime1);}

    public StringProperty time2Property(){return time2;}
    public String getTime21(){return time2.get();}
    public void seTime21(String newTime2){time2.set(newTime2);}

    public StringProperty fryProperty(){return fry;}
    public String getFry1(){return fry.get();}
    public void setFry1(String newFry){fry.set("800");}

    public StringProperty priceProperty(){return price;}
    public String getPrice1(){return price.get();}
    public void setPrice1(String newPrice){price.set(newPrice);}

    @Override
    public String toString(){
        return String.format("%s[Route=%s,From=%s,To=%s,Departure=%s,Arrival=%s,places in train=%s,Price=%s]",
                getClass().getName(), getRoute1(),getFrom1(),getTo1(),getTime11(),getTime21(),getFry1(),getPrice1()
        );
    }
}

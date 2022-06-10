package com.example.myproject;



public class RouteBuilder {

    private String title;
    private String dateFrom;
    private String dateTo;
    private String time1;
    private String time2;
    private String price;



    public RouteBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public RouteBuilder setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public RouteBuilder setDateTo(String dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public RouteBuilder setTime1(String time1) {
        this.time1 = time1;
        return this;
    }

    public RouteBuilder setTime2(String time2) {
        this.time2 = time2;
        return this;
    }

    public Route createRoute() {
        return new Route(title, dateFrom, dateTo, time1, time2,"800", price);
    }
}
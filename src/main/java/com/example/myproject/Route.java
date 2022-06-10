package com.example.myproject;




public class Route {

    public String title;
    private String dateFrom;
    private String dateTo;
    private String time1;
    private String time2;
    private String seats;
    private String price;

    public Route(String title, String dateFrom, String dateTo, String time1, String time2, String seats, String price) {

        this.title = title;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.time1 = time1;
        this.time2 = time2;
        this.seats = seats;
        this.price = price;
    }


    public Route() {

    }





    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateFrom() {
        return dateFrom;
    }
    public void setDateFrom(String dateFrom) {this.dateFrom = dateFrom;}

    public String getDateTo() {
        return dateTo;
    }
    public void setDateTo(String dateTo) {this.dateTo = dateTo;}

    public String getTime1() {
        return time1;
    }
    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }
    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getSeats() { return seats; }
    public void setSeats(String seats) {this.seats = seats;}

    public String getPrice() {return price;}
    public void setPrice(String price) {this.price = price;}
}



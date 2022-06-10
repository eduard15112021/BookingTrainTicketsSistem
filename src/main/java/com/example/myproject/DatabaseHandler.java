package com.example.myproject;



import java.sql.*;
import java.util.ArrayList;

import static com.example.myproject.AppUserController.RouteName;


public class DatabaseHandler extends Configs {
    Connection dbConnection;


    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");
        try {
            dbConnection = DriverManager.getConnection(connectionString,
                    dbUser,dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    public void signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USERS_FIRSTNAME + "," + Const.USERS_SURNAME + "," +
                Const.USERS_USERNAME + "," + Const.USERS_PASSWORD +  ")" +
                "VALUES(?,?,?,?)";


        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setString(1,user.getFirstname());
            prST.setString(2,user.getSurname());
            prST.setString(3,user.getUsername());
            prST.setString(4,user.getPassword());


            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";


        try {
            PreparedStatement prST = getDbConnection().prepareStatement(select);
            prST.setString(1,user.getUsername());
            prST.setString(2,user.getPassword());

            resSet = prST.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }


    public void addNewRoute(Route route){
        String insert = "INSERT INTO " + Const.ROUTE_TABLE + "(" + Const.ROUTE_TITLE + "," +
                Const.ROUTE_DATEFROM + "," + Const.ROUTE_DATETO + "," +
                Const.ROUTE_TIME1 + "," + Const.ROUTE_TIME2 + ","  + Const.ROUTE_FRY + ","+ Const.ROUTE_PRICE + ")" +
                "VALUES(?,?,?,?,?,?,?)";


        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert);

            prST.setString(1, route.getTitle());
            prST.setString(2, route.getDateFrom());
            prST.setString(3, route.getDateTo());
            prST.setString(4, route.getTime1());
            prST.setString(5, route.getTime2());
            prST.setString(6, route.getSeats());
            prST.setString(7, route.getPrice());

            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getTitleNames(Route route) {
        ResultSet resSet = null;
        ArrayList<String> routeListToDelete = new ArrayList<>();
        try {
            String query = "select distinct title from "+Const.ROUTE_TABLE+"";
            Statement statement = getDbConnection().createStatement();
            resSet =statement.executeQuery(query);
            while(resSet.next()){

                route.title = resSet.getString("title");
                routeListToDelete.add(route.title);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return routeListToDelete ;
    }

    public ArrayList getTimes(Route route) {
        ResultSet resSet = null;
        ArrayList<String> timeList = new ArrayList<>();
        try {
            String query = "select * from "+Const.ROUTE_TABLE+" where title="+ "'" + RouteName + "'";

            Statement statement = getDbConnection().createStatement();
            resSet =statement.executeQuery(query);
            while(resSet.next()){

                String time1 = resSet.getString("time1");
             //   timeList.add(time1);
                String time2 = resSet.getString("time2");
                timeList.add("Departure: "+time1+" , Arrival: "+time2);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(timeList);

        return timeList ;
    }
    public ArrayList getFryPlaces(Route route,int fry){
        ResultSet resSet = null;
        String chosenTime = ChooseTimeController.timeForCheck.substring(11,16);
        ArrayList<String> FryPlacesList = new ArrayList<>();
        try {
            String query = "select * from "+Const.ROUTE_TABLE+" where title=" + "'" + RouteName + "' and time1 =" + "'" + chosenTime + "'";


            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(query);
            int price=1;
            while (resSet.next()) {

                price = resSet.getInt("price");
                TakeSeatController.price=price;
            }
            if (fry >= 10){
                for (int i=1;i<11;i++){
                    FryPlacesList.add(""+i+" - price: "+i*price);
                }
            }else for (int i=1;i<=fry;i++){
                FryPlacesList.add(""+i+" - price: "+i*price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(FryPlacesList);

        return FryPlacesList ;
    }

    public void addNewBooking(Booking booking){
        String insert = "INSERT INTO " + Const.BOOKING_TABLE + "(" +
                Const.BOOKINGS_USERNAME + "," + Const.BOOKINGS_TITLE + "," +
                Const.BOOKINGS_DATE + "," + Const.BOOKINGS_TIME + "," +
                Const.BOOKINGS_SEATS + "," + Const.BOOKINGS_PAYED + ")" +
                "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setString(1,booking.getUsername());
            prST.setString(2,booking.getTitle());
            prST.setString(3,booking.getDate());
            prST.setString(4,booking.getTime());
            prST.setString(5,booking.getSeats());
            prST.setString(6,booking.getPayed());


            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}



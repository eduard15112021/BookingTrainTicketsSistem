package com.example.myproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import static com.example.myproject.AppUserController.RouteName;

public class ChooseDateController extends Configs {

    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
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


    @FXML
    private Button backButton;

    @FXML
    private Button confirmButton;

    @FXML
    private DatePicker datePicker;
    
    @FXML
    private Label labelPickTheDate;

    @FXML
    static String dateForCheck;

    @FXML
    void initialize() {

        System.out.println(RouteName);

        Integer yearFrom = getYearFrom();
        Integer monthFrom = getMonthFrom();
        Integer dayFrom = getDayFrom();

        Integer yearTo = getYearTo();
        Integer monthTo = getMonthTo();
        Integer dayTo = getDayTo();

        LocalDate todayDate = LocalDate.now();


        DatePicker minDate = new DatePicker();
            minDate.setValue(LocalDate.of(yearFrom, monthFrom, dayFrom));
            if (todayDate.isAfter(minDate.getValue())){
                minDate.setValue(LocalDate.now());
                datePicker.setValue(LocalDate.now());

            }

        DatePicker maxDate = new DatePicker();
            maxDate.setValue(LocalDate.of(yearTo, monthTo, dayTo));
        final Callback<DatePicker, DateCell> dayCellFactory;
        dayCellFactory =(final DatePicker datePicker)-> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty){
                super.updateItem(item, empty);
                if(item.isAfter(maxDate.getValue())){
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb");
                }
                if(item.isBefore(minDate.getValue())){
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb");
                }
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);


        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("appuser.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });

        confirmButton.setOnAction(event -> {
            confirmButton.getScene().getWindow().hide();
            System.out.println(datePicker.getValue());
            dateForCheck = String.valueOf(datePicker.getValue());

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("choosetime.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();

                }

                Parent root = loader.getRoot();
                Stage stage = new Stage ();
                stage.setScene(new Scene(root));
                stage.show();

        });

        getDateFromDB();
        getDateToDB();
    }


    public String getDateTo(Route route) {
        ResultSet resSet = null;
        String dateTo = null;
        try {
            String query = "select * from a1.routs where title="+ "'" + RouteName + "'";
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(query);
            while (resSet.next()) {

                dateTo = String.valueOf(resSet.getString("dateTo"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dateTo;
    }

    public String getDateFrom(Route route) {
        ResultSet resSet = null;
        String dateFrom = null;
        try {
            String query = "select * from a1.routs where title="+ "'" + RouteName + "'";
            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(query);
            while (resSet.next()) {

                dateFrom = String.valueOf(resSet.getString("dateFrom"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dateFrom;
    }







    private String getDateFromDB(){

        Route route = new Route();
        String dateFromDB = getDateFrom(route);
        return dateFromDB;
    }

    private String getDateToDB(){
        Route route = new Route();
        String dateToDB = getDateTo(route);
        return dateToDB;
    }






    private Integer getYearFrom(){
        Integer yearFrom = Integer.valueOf(getDateFromDB().substring(0,4));
        return yearFrom;
    }
    private Integer getMonthFrom(){
        Integer monthFrom = Integer.valueOf(getDateFromDB().substring(5,7));
        return monthFrom;
    }
    private Integer getDayFrom(){
        Integer dayFrom = Integer.valueOf(getDateFromDB().substring(8,10));
        return dayFrom;
    }



    private Integer getYearTo(){
        Integer yearTo = Integer.valueOf(getDateToDB().substring(0,4));
        return yearTo;
    }
    private Integer getMonthTo() {
        Integer monthTo = Integer.valueOf(getDateToDB().substring(5, 7));
        return monthTo;
    }
    private Integer getDayTo() {
        Integer dayTo = Integer.valueOf(getDateToDB().substring(8, 10));
        return dayTo;
    }

}

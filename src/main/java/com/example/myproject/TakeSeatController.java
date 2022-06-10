package com.example.myproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static com.example.myproject.AppUserController.RouteName;

public class TakeSeatController extends Configs {

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
    private ChoiceBox numseatList;

    @FXML
    private Label lostSeats;

    static Integer quantity;

    static String seatsForCheck;

    static Integer price;



    @FXML
    void initialize() {
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("choosetime.fxml"));

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
        lostSeats.setText(""+newFry()+" tickets left");

        confirmButton.setOnAction(event -> {
            confirmButton.getScene().getWindow().hide();
            seatsForCheck = String.valueOf(numseatList.getValue());

            quantity =Integer.parseInt(seatsForCheck.substring(0, TakeSeatController.seatsForCheck.indexOf(' ')));

            updateFry();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("paying.fxml"));


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
        quantity = 0;


        showSeats();

    }



private void showSeats(){
    DatabaseHandler dbHandler = new DatabaseHandler();
    Route route = new Route();

    ArrayList result = dbHandler.getFryPlaces(route,newFry());

    numseatList.setValue(result.get(0));
    ObservableList<ArrayList> seatList = FXCollections.observableArrayList(result);
    numseatList.setItems(seatList);
}


    void updateFry(){
        String chosenDate = ChooseDateController.dateForCheck.substring(5);
        String chosenTime = ChooseTimeController.timeForCheck.substring(11,16);
        String query = "select * from " + Const.ROUTE_TABLE + " where title=" + "'" + RouteName + "' and time1 =" + "'" + chosenTime + "'";
        Statement statement = null;
        int id=1;
        try {
            statement = getDbConnection().createStatement();

        ResultSet resSet = statement.executeQuery(query);
        String bought="";
        while (resSet.next()) {
            bought = resSet.getString("bought");
            id = resSet.getInt("idroute");
        }
            int index = bought.indexOf(chosenDate);
            if (index == -1) {
                bought=bought.concat(chosenDate+quantity+"   ");
            }else{
                int num = Integer.parseInt(bought.substring(index+5,index+8).trim());
                int num1= num+quantity;
                bought=bought.replaceFirst(chosenDate+num,chosenDate+num1);
            }
        query = "update " + Const.ROUTE_TABLE + " set bought =" +" '"+ bought + "'" +" where (" + Const.ROUTE_ID + " =" + id+ ");";
        PreparedStatement ps = getDbConnection().prepareStatement(query);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    int newFry(){
        ResultSet resSet = null;
        String chosenDate = ChooseDateController.dateForCheck.substring(5);;
        String chosenTime = ChooseTimeController.timeForCheck.substring(11,16);
        int fry=800;
        try {
            String query = "select * from " + Const.ROUTE_TABLE + " where title=" + "'" + RouteName + "' and time1 =" + "'" + chosenTime + "'";

            Statement statement = getDbConnection().createStatement();
            resSet = statement.executeQuery(query);
            String bought="";
             fry=800;
            while (resSet.next()) {
                bought = resSet.getString("bought");
                fry = resSet.getInt("fryplace");
            }
            bought=bought.concat(chosenDate);

                int index = bought.indexOf(chosenDate);
            int num;
            if (index == -1) {
                num=0;
                }else {
                num= Integer.parseInt(bought.substring(index+5,index+8).trim());
            }

            fry-=num;
            return fry;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return fry;
        }
    }
}

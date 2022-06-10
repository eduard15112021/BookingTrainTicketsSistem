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

public class DeleteRouteController extends Configs {

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


    @FXML
    private Button backButton;

    @FXML
    private Button deleteButton;

    @FXML
    public ChoiceBox routeListDelete;

    @FXML
    private Label labelRouteDeleted;


    @FXML
    void initialize() {

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("manageRouts.fxml"));

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

        deleteButton.setOnAction(event -> {

            deleteRoute();
            showRoute();
            labelRouteDeleted.setVisible(true);
        });

        showRoute();
    }


    private void showRoute() {

        DatabaseHandler dbHandler = new DatabaseHandler();
        Route route = new Route();

        ArrayList result = dbHandler.getTitleNames(route);

        routeListDelete.setValue(result.get(0));
        ObservableList<ArrayList> routeList = FXCollections.observableArrayList(result);
        routeListDelete.setItems(routeList);
    }



    private void deleteRoute(){

        String name = (String) routeListDelete.getValue();
        System.out.print("Route ");
        System.out.print(name);
        System.out.print(" deleted");


        String delete = "DELETE FROM "+Const.ROUTE_TABLE+"" + " WHERE title =" + "'" + name + "'";

        PreparedStatement prST = null;
        try {
            prST = getDbConnection().prepareStatement(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prST.executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

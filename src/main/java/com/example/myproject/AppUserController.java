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

public class AppUserController extends Configs{

    Connection dbConnection;



    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        try {
            dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/a1", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }

    @FXML
    private Button logOutButton;

    @FXML
    private Button confirmButton;

    @FXML
    public ChoiceBox routeList;

    @FXML
    private Label routeNotChosen;

    @FXML
    static public String RouteName;


    @FXML
    void initialize(){
        showRoute();

        confirmButton.setOnAction(event -> {
            confirmButton.getScene().getWindow().hide();
            RouteName = (String) (routeList.getValue());;
            System.out.println(RouteName);
            if(RouteName.equals("")){
            System.out.println("Error");
            routeNotChosen.setVisible(true);
            }
            else{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("choosedate.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();

                }

                Parent root = loader.getRoot();
                    Stage stage = new Stage ();
                    stage.setScene(new Scene(root));
                    stage.show();

            }
        });

        logOutButton.setOnAction(event -> {
            logOutButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("hello-view.fxml"));

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
    }
    private void showRoute() {

        DatabaseHandler dbHandler = new DatabaseHandler();
        Route route = new Route();

        ArrayList result = dbHandler.getTitleNames(route);

        routeList.setValue(showFirstRoute());
        ObservableList<ArrayList> routeListToDelete = FXCollections.observableArrayList(result);
        routeList.setItems(routeListToDelete);
    }

    public ArrayList getTitleNames(Route route) {
        ResultSet resSet = null;
        ArrayList<String> routeListToDelete = new ArrayList<>();
        try {
            String query = "select * from routs";
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

    private String showFirstRoute() {

        DatabaseHandler dbHandler = new DatabaseHandler();
        Route route = new Route();

        ArrayList result = dbHandler.getTitleNames(route);



        ObservableList<ArrayList> routeList = FXCollections.observableArrayList(result);

        return String.valueOf(routeList.get(0));



    }

}

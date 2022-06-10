package com.example.myproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ViewRoutsController extends Configs{

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
    private TableView<RouteItem> routeTable;


    @FXML
    private TableColumn<RouteItem, String> routeColumn;
    @FXML
    private TableColumn<RouteItem, String> fromColumn;
    @FXML
    private TableColumn<RouteItem, String> toColumn;
    @FXML
    private TableColumn<RouteItem, String> departureColumn;
    @FXML
    private TableColumn<RouteItem, String> arrivalColumn;
    @FXML
    private TableColumn<RouteItem, String> fryColumn;
    @FXML
    private TableColumn<RouteItem, String> priceColumn;


    @FXML
    private Button backButton;

    ObservableList<RouteItem> routList = FXCollections.observableArrayList();


    @FXML
    void initialize(){
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("appadmin.fxml"));

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
        //showRoute();
        Route route = new Route();
        getTable(route);
        routeTable.setItems(routList);
    }

    public ObservableList<RouteItem> getTable(Route route) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        ResultSet resSet = null;

        try {
            String query = "SELECT * FROM "+Const.ROUTE_TABLE+"" ;

            Statement statement = getDbConnection().createStatement();
            resSet =statement.executeQuery(query);

            while(resSet.next()){

                RouteItem routeItem = new RouteItem();


                routeItem.setRoute1(resSet.getString("title"));
                System.out.println(resSet.getString("title"));

                routeItem.setFrom1(resSet.getString("dateFrom"));
                System.out.println(resSet.getString("dateFrom"));

                routeItem.setTo1(resSet.getString("dateTo"));
                System.out.println(resSet.getString("dateTo"));

                routeItem.setTime11(resSet.getString("time1"));
                System.out.println(resSet.getString("time1"));

                routeItem.seTime21(resSet.getString("time2"));
                System.out.println(resSet.getString("time2"));

                routeItem.setFry1(resSet.getString("fryplace"));
                System.out.println(resSet.getString("fryplace"));

                routeItem.setPrice1(resSet.getString("price"));
                System.out.println(resSet.getString("price"));

                routList.add(routeItem);
                System.out.println(routeItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        routeColumn.setCellValueFactory(f->f.getValue().routeProperty());
        fromColumn.setCellValueFactory(f->f.getValue().fromProperty());
        toColumn.setCellValueFactory(f->f.getValue().toProperty());
        departureColumn.setCellValueFactory(f->f.getValue().time1Property());
        arrivalColumn.setCellValueFactory(f->f.getValue().time2Property());
        fryColumn.setCellValueFactory(f->f.getValue().fryProperty());
        priceColumn.setCellValueFactory(f->f.getValue().priceProperty());

        return routList;
    }

    private void initTable(){


        routeColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("dateTo"));
        departureColumn.setCellValueFactory(new PropertyValueFactory<>("time1"));
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("time2"));
        fryColumn.setCellValueFactory(new PropertyValueFactory<>("fryplace"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}

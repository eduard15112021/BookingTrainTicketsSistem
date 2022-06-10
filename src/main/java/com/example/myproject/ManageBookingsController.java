package com.example.myproject;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class ManageBookingsController extends Configs{

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
    private TableView<BookingItem>bookingsTable;


    @FXML
    private TableColumn<BookingItem, String> usernameColumn;
    @FXML
    private TableColumn<BookingItem, String> titleColumn;
    @FXML
    private TableColumn<BookingItem, String> dateColumn;
    @FXML
    private TableColumn<BookingItem, String> timeColumn;
    @FXML
    private TableColumn<BookingItem, String> seatsColumn;
    @FXML
    private TableColumn<BookingItem, String> payedColumn;

    @FXML
    private Button backButton;

    ObservableList<BookingItem> bookingsList = FXCollections.observableArrayList();






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


        Booking booking =new Booking();
        getTable(booking);

        // initTable();

        //System.out.println(bookingsList);
        bookingsTable.setItems(bookingsList);
    }


    public ObservableList<BookingItem> getTable(Booking booking) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        ResultSet resSet = null;

        try {

            String query = "SELECT * FROM bookings" ;


            Statement statement = getDbConnection().createStatement();
            resSet =statement.executeQuery(query);

            while(resSet.next()){

                BookingItem bookings = new BookingItem();


                bookings.setUsername1(resSet.getString("username"));
                System.out.println(resSet.getString("username"));


                bookings.setTitle1(resSet.getString("title"));
                System.out.println(resSet.getString("title"));

                bookings.setDate1(resSet.getString("date"));
                System.out.println(resSet.getString("date"));

                bookings.setTime1(resSet.getString("time"));
                System.out.println(resSet.getString("time"));

                bookings.setSeats1(resSet.getString("seats"));
                System.out.println(resSet.getString("seats"));

                bookings.setPayed1(resSet.getString("payed"));
                System.out.println(resSet.getString("payed"));

                bookingsList.add(bookings);
                System.out.println(bookings);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        usernameColumn.setCellValueFactory(f->f.getValue().usernameProperty());
        titleColumn.setCellValueFactory(f->f.getValue().titleProperty());
        dateColumn.setCellValueFactory(f->f.getValue().dateProperty());
        timeColumn.setCellValueFactory(f->f.getValue().timeProperty());
        seatsColumn.setCellValueFactory(f->f.getValue().seatsProperty());
        payedColumn.setCellValueFactory(f->f.getValue().payedProperty());

         return bookingsList;
    }



    private void initTable(){

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
        payedColumn.setCellValueFactory(new PropertyValueFactory<>("payed"));

    }












}

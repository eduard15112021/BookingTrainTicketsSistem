package com.example.myproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.stage.Stage;


import java.io.IOException;


import javafx.scene.control.DatePicker;

import javafx.scene.control.TextField;

import javafx.scene.control.Button;





public class AddRouteController {


    @FXML
    private Button backButton;

    @FXML
    private TextField timeStatusBox1;

    @FXML
    private TextField timeStatusBox2;


    @FXML
    private TextField priceform;


    @FXML
    private TextField TitleField;

    @FXML
    private DatePicker DateFrom;

    @FXML
    private DatePicker DateTo;

    @FXML
    private Button AddRouteButton;

    @FXML
    private Label notAllFieldsAreFilled;

    @FXML
    private Label routeAddedLabel;

    @FXML
    private Label myRouteName;







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


        AddRouteButton.setOnAction(actionEvent -> {


            String titleName = TitleField.getText().trim();
            String dateFromValue = String.valueOf(DateFrom.getValue());
            String dateToValue = String.valueOf(DateTo.getValue());
            String watchTime1 = timeStatusBox1.getText();
            String watchTime2 = timeStatusBox2.getText();
            int price =  Integer.parseInt(priceform.getText());

            if(
                    !titleName.equals("") &&
                    !dateFromValue.equals("null") &&
                    !dateToValue.equals("null") &&
                    !watchTime1.equals("") &&
                    !watchTime2.equals("")) {
                addNewRoute();
                notAllFieldsAreFilled.setVisible(false);
                routeAddedLabel.setVisible(true);
                myRouteName.setText(titleName);

            }
            else
                notAllFieldsAreFilled.setVisible(true);
        });
    }
    private void addNewRoute(){
        DatabaseHandler dbHandler = new DatabaseHandler();


        String title = TitleField.getText();
        String dateFrom = String.valueOf(DateFrom.getValue());
        String dateTo = String.valueOf(DateTo.getValue());
        String time1 = timeStatusBox1.getText();
        String time2 = timeStatusBox2.getText();
        String price =  priceform.getText();

        Route route = new Route(title, dateFrom, dateTo,time1,time2,"800", price);

        dbHandler.addNewRoute(route);

        System.out.println(TitleField.getText());
        System.out.println(DateFrom.getValue());
        System.out.println(DateTo.getValue());
        System.out.println(timeStatusBox1.getText());
        System.out.println(timeStatusBox1.getText());
    }


}

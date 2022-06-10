package com.example.myproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ChooseTimeController {

    @FXML
    private Button backButton;

    @FXML
    private Button chooseButton;

    @FXML
    private ChoiceBox timeList;

    @FXML
    static String timeForCheck;

    @FXML
    void initialize(){
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("choosedate.fxml"));

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
        chooseButton.setOnAction(event -> {
            chooseButton.getScene().getWindow().hide();
            System.out.println(timeList.getValue());
            timeForCheck= String.valueOf(timeList.getValue());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("takeseat.fxml"));

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
        showTime();
    }


    private void showTime() {

        DatabaseHandler dbHandler = new DatabaseHandler();
        Route route = new Route();

        ArrayList result = dbHandler.getTimes(route);

        timeList.setValue(result.get(0));
        ObservableList<ArrayList> routeList = FXCollections.observableArrayList(result);
        timeList.setItems(routeList);
    }

}

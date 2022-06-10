package com.example.myproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BookedSuccessfullyController {

    @FXML
    private Button newBookingButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Label usernameCheck;

    @FXML
    private Label titleCheck;

    @FXML
    private Label dateCheck;

    @FXML
    private Label timeCheck;

    @FXML
    private Label seatsCheck;


    @FXML
    private Label payedCheck;

    @FXML
     void initialize() {
        String seatsCheck1=String.valueOf(TakeSeatController.seatsForCheck);
        seatsCheck1.substring(1);
        usernameCheck.setText(SignInController.usernameForCheck);
        titleCheck.setText(AppUserController.RouteName);
        dateCheck.setText(ChooseDateController.dateForCheck);
        timeCheck.setText(ChooseTimeController.timeForCheck);
        seatsCheck.setText(String.valueOf(TakeSeatController.quantity));
        payedCheck.setText(String.valueOf(TakeSeatController.quantity*TakeSeatController.price));


        newBookingButton.setOnAction(event -> {
            newBookingButton.getScene().getWindow().hide();

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
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        });
    }


}

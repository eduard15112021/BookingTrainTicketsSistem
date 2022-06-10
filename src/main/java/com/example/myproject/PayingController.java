package com.example.myproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PayingController {

    ObservableList<String> mmList = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12");
    ObservableList<String> yyyyList = FXCollections.observableArrayList("2021","2022","2023","2024","2025","2026","2027","2028","2029","2030");

    @FXML
    private Button backButton;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cvvField;

    @FXML
    private Button confirmPaymentButton;

    @FXML
    private Label quantityLabel;

    @FXML
    private Label toPayLabel;

    @FXML
    private ChoiceBox mmBox;

    @FXML
    private ChoiceBox yyyyBox;

    @FXML
    void initialize() {

        mmBox.setValue("01");
        mmBox.setItems(mmList);

        yyyyBox.setValue("2021");
        yyyyBox.setItems(yyyyList);


        cardNumberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")){
                    cardNumberField.setText(newValue.replaceAll("[^\\d]",""));
                }
            }
        });

        cvvField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")){
                    cvvField.setText(newValue.replaceAll("[^\\d]",""));
                }
            }
        });



        quantityLabel.setText(String.valueOf(TakeSeatController.quantity));
        toPayLabel.setText(String.valueOf(TakeSeatController.quantity*10));
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

        confirmPaymentButton.setOnAction(event -> {
            confirmPaymentButton.getScene().getWindow().hide();

            System.out.print("Card Number: ");
            System.out.println(cardNumberField.getText().trim());

            System.out.print("Validity: ");
            System.out.print(mmBox.getValue());
            System.out.print(" / ");
            System.out.println(yyyyBox.getValue());

            System.out.print("CVV/CVC: ");
            System.out.println(cvvField.getText().trim());



            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("bookedsuccessfully.fxml"));


            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();

            }


            Parent root = loader.getRoot();
            Stage stage = new Stage ();
            stage.setScene(new Scene(root));
            stage.show();
            addNewBooking();

        });

        addTextLimiter(cardNumberField,16);
        addTextLimiter(cvvField,3);

    }
    public static void addTextLimiter(final TextField tf, final int maxLength){
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if(tf.getText().length()>maxLength){
                    String s =tf.getText().substring(0,maxLength);
                    tf.setText(s);
                }
            }
        });
    }
    private void addNewBooking(){
        DatabaseHandler dbHandler = new DatabaseHandler();

        String username = SignInController.usernameForCheck;
        String title = AppUserController.RouteName;
        String date = ChooseDateController.dateForCheck;
        String time = ChooseTimeController.timeForCheck.substring(11,16);
        String seats = TakeSeatController.seatsForCheck.substring(0, 2).trim();
        String payed = String.valueOf(TakeSeatController.quantity*TakeSeatController.price);

        Booking booking = new Booking(username, title, date, time, seats,payed);

        dbHandler.addNewBooking(booking);
        System.out.println(SignInController.usernameForCheck);
        System.out.println(AppUserController.RouteName);
        System.out.println(ChooseDateController.dateForCheck);
        System.out.println(ChooseTimeController.timeForCheck.substring(11,16));
        System.out.println(TakeSeatController.seatsForCheck.substring(0, 2).trim());
        System.out.println(String.valueOf(TakeSeatController.quantity*TakeSeatController.price));
    }
}

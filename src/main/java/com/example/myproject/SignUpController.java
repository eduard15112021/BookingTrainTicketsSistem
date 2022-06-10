package com.example.myproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField SignUpName;

    @FXML
    private TextField SignUpSurname;

    @FXML
    private Label notAllFieldsAreFilled;

    @FXML
    private Button backButton;


    @FXML
    void initialize() {

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();

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


        SignUpButton.setOnAction(actionEvent -> {

                    String signUpLogin = LoginField.getText().trim();
                    String signUpPassword = PasswordField.getText().trim();
                    String signUpName = SignUpName.getText().trim();
                    String signUpSurname = SignUpSurname.getText().trim();

                         if(!signUpLogin.equals("") &&
                            !signUpPassword.equals("") &&
                            !signUpName.equals("") &&
                            !signUpSurname.equals("")) {
                             signUpNewUser();

                                 openNewScene("hello-view.fxml");

                         }
                    else
                        notAllFieldsAreFilled.setVisible(true);
                });

    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstname = SignUpName.getText();
        String surname = SignUpSurname.getText();
        String username = LoginField.getText();
        String password = PasswordField.getText();

        User user = new User(firstname, surname, username, password);

        dbHandler.signUpUser(user);

    }
    public void openNewScene(String window){
        SignUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

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

}
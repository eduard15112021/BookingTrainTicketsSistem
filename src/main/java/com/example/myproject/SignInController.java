package com.example.myproject;

import com.example.myproject.animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInController {

    @FXML
    private Button SignInButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Label wrongCredentials;

    @FXML
    private Button backButton;

    @FXML
    private Label emptyLoginOrPassword;

    @FXML
    static String usernameForCheck;


    @FXML
    void initialize(){

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
            Stage stage = new Stage ();
            stage.setScene(new Scene(root));
            stage.show();
        });


        SignInButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText,loginPassword);
                usernameForCheck = loginText;
            }
            else
                emptyLoginOrPassword.setVisible(true);
    });
    }

    private  void loginUser(String loginText, String loginPassword){
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;


        while(true){
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            counter++;
        }
        if (counter>= 1) {
            if(loginText.equals("admin") && loginPassword.equals("admin")){
                    openNewScene("appadmin.fxml");
                }
                else

                openNewScene("appuser.fxml");

        }
            else{
            emptyLoginOrPassword.setVisible(false);
            wrongCredentials.setVisible(true);
                Shake userLoginAnim = new Shake(login_field);
                Shake userPasswordAnim = new Shake(password_field);
                userLoginAnim.playAnim();
                userPasswordAnim.playAnim();
            }

    }
    public void openNewScene(String window){
        SignInButton.getScene().getWindow().hide();

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





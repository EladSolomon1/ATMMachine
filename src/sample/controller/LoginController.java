package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Database.Const;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.model.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {


    @FXML
    private ResourceBundle resources;


    @FXML
    private JFXTextField LoginAccountNumber;

    @FXML
    private JFXPasswordField LoginPassword;

    @FXML
    private JFXButton LoginButton;

    @FXML
    private JFXButton LoginToSignUp;

    private DatabaseHandler databaseHandler;


    @FXML
    void initialize() {
        databaseHandler=new DatabaseHandler();

        LoginButton.setOnAction(event -> {
            int loginAccountNumber= Integer.parseInt(LoginAccountNumber.getText().trim());
            String loginPassword=LoginPassword.getText().trim();

            User user=new User();
            user.setCustomer_number(loginAccountNumber);
            user.setPassword(loginPassword);

            ResultSet userRow=databaseHandler.getUserByAccountNumberAndPassword(user);

            int counter=0;
            try {
                    while (userRow.next()){
                        counter++;
                    }
                    if (counter==1){
                        showMenuScreen(LoginAccountNumber.getText());
                    } else{
                        Shaker userNameShaker=new Shaker(LoginAccountNumber);
                        Shaker passwordShaker=new Shaker(LoginPassword);
                        passwordShaker.shake();
                        userNameShaker.shake();
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        LoginToSignUp.setOnAction(event -> {
            LoginToSignUp.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("SignUp");
            stage.show();

        });


    }
    private void showMenuScreen(String accountNumber){
        LoginButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/menu.fxml"));

        try {
            loader.load();
            MenuController menuController=loader.getController();
            menuController.myFunction(accountNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root=loader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Menu");
        stage.showAndWait();

    }
}

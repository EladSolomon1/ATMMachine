package sample.controller;

import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private JFXTextField signUpFirstName;

    @FXML
    private JFXTextField signUpLastName;

    @FXML
    private JFXCheckBox signUpCheckBoxMale;

    @FXML
    private JFXCheckBox signUpCheckBoxFemale;

    @FXML
    private JFXPasswordField signUpPassword;

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXTextField  signUpEmail;

    @FXML
    private ImageView signUpBackImage;


    @FXML
    void initialize() {
        signUpButton.setOnAction(event -> {
            createUser();
            signUpButton.getScene().getWindow().hide();
//            FXMLLoader loader2=new FXMLLoader();
//            loader2.setLocation(getClass().getResource("/sample/view/popup.fxml"));
//            try {
//                loader2.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

//            FXMLLoader loader=new FXMLLoader();
//            loader.setLocation(getClass().getResource("/sample/view/popup.fxml"));
//            try {
//                Parent root=(Parent) loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            PopUpController popupcontroller=loader.getController();
//            popupcontroller.myFunction();


            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/popup.fxml"));
            try {
                loader.load();
                PopUpController popupcontroller=loader.getController();
                popupcontroller.myFunction(signUpEmail.getText());
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        signUpBackImage.setOnMouseClicked(event -> {
            signUpBackImage.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/login.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        });

    }
    private void createUser(){

        DatabaseHandler databaseHandler=new DatabaseHandler();

        String name=signUpFirstName.getText();
        String lastName=signUpLastName.getText();
        String password=signUpPassword.getText();
        String email=signUpEmail.getText();
        String gender="";
        if (signUpCheckBoxFemale.isSelected())
            gender="Female";
        else gender="Male";

        User user=new User(name,lastName,password,email,gender);
        databaseHandler.signupUser(user);
    }
}

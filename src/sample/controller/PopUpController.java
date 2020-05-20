package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Database.Const;
import sample.Database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PopUpController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private Label PopUpAcountNumber;

    @FXML
    private Label PopUpSuccess;

    @FXML
    private JFXButton PopUpToLogin;

    @FXML
    private Label PopUpNumberFieldLabel;

    @FXML
    void initialize() throws SQLException {

        PopUpToLogin.setOnAction(event -> {
            PopUpToLogin.getScene().getWindow().hide();
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
    public void myFunction(String text) throws SQLException {
        int accountNumber;
        DatabaseHandler databaseHandler=new DatabaseHandler();
        User user=new User();
        user.setEmail(text);

        ResultSet userRow=databaseHandler.getUserByEmail(user);
        while(userRow.next()){
            accountNumber=userRow.getInt(Const.ACCOUNTS_ACCOUNT_NUMBER);
            PopUpNumberFieldLabel.setText(String.valueOf(accountNumber));
        }
    }

}

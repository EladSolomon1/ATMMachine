package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Database.Const;
import sample.Database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BalanceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView BalanceBackImage;

    @FXML
    private Label BalanceNumberFieldLabel;

    @FXML
    private Label BalanceBalanceFieldLabel;

    @FXML
    private JFXButton BalanceExit;

    @FXML
    void initialize() {

        BalanceBackImage.setOnMouseClicked(event -> {
            BalanceBackImage.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/menu.fxml"));
            try {
                loader.load();
                MenuController menuController=loader.getController();
                menuController.myFunction(BalanceNumberFieldLabel.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu");
            stage.show();

        });

        BalanceExit.setOnAction(event -> {
            BalanceExit.getScene().getWindow().hide();
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
    public void myFunction(String accountNumber) {
        BalanceNumberFieldLabel.setText(accountNumber);
        DatabaseHandler databaseHandler = new DatabaseHandler();
        User user = new User();
        user.setCustomer_number(Integer.parseInt(accountNumber));

        ResultSet userRow = databaseHandler.getUserByAccountNumber(user);
        Double balance;
        try {
            while (userRow.next()) {
                balance = userRow.getDouble(Const.ACCOUNTS_BALANCE);
                BalanceBalanceFieldLabel.setText(String.valueOf(balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

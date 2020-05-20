package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DepositController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView DepositBackImage;

    @FXML
    private JFXButton DepositExit;

    @FXML
    private Label DepositAcountNumberLabel;

    @FXML
    private Label DepositNumberFieldLabel;

    @FXML
    private JFXTextField DepositAmountToDeposit;

    @FXML
    private Label DepositAnswerLabel;

    @FXML
    private JFXButton DepositButton;

    @FXML
    void initialize() {

        DepositButton.setOnAction(event -> {
            double amountToDeposit= Double.parseDouble(DepositAmountToDeposit.getText());

            DatabaseHandler databaseHandler=new DatabaseHandler();
            int accountNumber= Integer.parseInt(DepositNumberFieldLabel.getText());

            User user=new User();
            user.setCustomer_number(accountNumber);

            ResultSet userRow = databaseHandler.getUserByAccountNumber(user);
            double balance;

            int delay = 2000;
            ActionListener labelPerformer;

            try {
                while (userRow.next()) {
                    balance = userRow.getDouble(Const.ACCOUNTS_BALANCE);
                    if (amountToDeposit>0){
                        double updateAmount=balance+amountToDeposit;
                        databaseHandler.updateAccount(user,updateAmount);
                        DepositAnswerLabel.setText("Success!");
                        labelPerformer= e -> DepositAnswerLabel.setVisible(false);
                        new javax.swing.Timer(delay,labelPerformer).start();
                    }
                    else if (amountToDeposit<0){
                        DepositAnswerLabel.setText("You cannot enter a negative amount.");
                        labelPerformer= e -> DepositAnswerLabel.setVisible(false);
                        new javax.swing.Timer(delay,labelPerformer).start();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DepositAnswerLabel.setVisible(true);

        });

        DepositBackImage.setOnMouseClicked(event -> {
            DepositBackImage.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/menu.fxml"));
            try {
                loader.load();
                MenuController menuController=loader.getController();
                menuController.myFunction(DepositNumberFieldLabel.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu");
            stage.show();

        });

        DepositExit.setOnAction(event -> {
            DepositExit.getScene().getWindow().hide();
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
        DepositNumberFieldLabel.setText(accountNumber);
    }
}

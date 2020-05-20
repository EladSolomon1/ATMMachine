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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WithdrawalController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField WithdrawAccountAmountToWithdraw;

    @FXML
    private ImageView WithdrawBackImage;

    @FXML
    private Label WithdrawalAnswerLabel;

    @FXML
    private JFXButton WithdrawalExit;

    @FXML
    private Label WithdrawalNumberFieldLabel;

    @FXML
    private JFXButton WithdrawButton;

    @FXML
    void initialize() {

        WithdrawButton.setOnAction(event -> {
            double amountToWithdrawal= Double.parseDouble(WithdrawAccountAmountToWithdraw.getText());

            DatabaseHandler databaseHandler=new DatabaseHandler();
            int accountNumber= Integer.parseInt(WithdrawalNumberFieldLabel.getText());

            User user=new User();
            user.setCustomer_number(accountNumber);

            ResultSet userRow = databaseHandler.getUserByAccountNumber(user);
            double balance;
            int delay = 2000;
            try {
                while (userRow.next()) {
                    balance = userRow.getDouble(Const.ACCOUNTS_BALANCE);
                    if (amountToWithdrawal>0&&balance>amountToWithdrawal){
                        double updateAmount=balance-amountToWithdrawal;
                        databaseHandler.updateAccount(user,updateAmount);
                        WithdrawalAnswerLabel.setText("Success!");
                        ActionListener labelPerformer= e -> WithdrawalAnswerLabel.setVisible(false);
                        new javax.swing.Timer(delay,labelPerformer).start();
                    }
                    else {
                        WithdrawalAnswerLabel.setText("Failed! Limited account.");
                        ActionListener labelPerformer= e -> WithdrawalAnswerLabel.setVisible(false);
                        new javax.swing.Timer(delay,labelPerformer).start();
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            WithdrawalAnswerLabel.setVisible(true);

        });

        WithdrawBackImage.setOnMouseClicked(event -> {
            WithdrawBackImage.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/menu.fxml"));
            try {
                loader.load();
                MenuController menuController=loader.getController();
                menuController.myFunction(WithdrawalNumberFieldLabel.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu");
            stage.show();

        });

        WithdrawalExit.setOnAction(event -> {
            WithdrawalExit.getScene().getWindow().hide();
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
        WithdrawalNumberFieldLabel.setText(accountNumber);
    }
}

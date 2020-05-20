package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController {

    //public static int accountNumber;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton MenuBalance;

    @FXML
    private JFXButton MenuWithdraw;

    @FXML
    private JFXButton MenuDeposit;

    @FXML
    private JFXButton MenuExit;

    @FXML
    private Label MenuAcountNumberLabel;

    @FXML
    private Label MenuNumberFieldLabel;

    @FXML
    void initialize() {

        MenuBalance.setOnAction(event -> {
            MenuBalance.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/balance.fxml"));
            try {
                loader.load();
                BalanceController balanceController=loader.getController();
                balanceController.myFunction(MenuNumberFieldLabel.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Balance");
            stage.show();

        });

        MenuWithdraw.setOnAction(event -> {
            MenuWithdraw.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/withdraw.fxml"));
            try {
                loader.load();
                WithdrawalController withdrawalController=loader.getController();
                withdrawalController.myFunction(MenuNumberFieldLabel.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Withdrawal");
            stage.show();

        });

        MenuDeposit.setOnAction(event -> {
            MenuWithdraw.getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/deposit.fxml"));
            try {
                loader.load();
                DepositController depositController=loader.getController();
                depositController.myFunction(MenuNumberFieldLabel.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root=loader.getRoot();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Deposit");
            stage.show();
        });

        MenuExit.setOnAction(event -> {

            MenuExit.getScene().getWindow().hide();
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
//    public  int getAccountNumber() { return accountNumber; }
//
//    public  void setAccountNumber(int accountNumber) { MenuController.accountNumber = accountNumber; }

    public void myFunction(String accountNumber){
        MenuNumberFieldLabel.setText(accountNumber);
    }

}

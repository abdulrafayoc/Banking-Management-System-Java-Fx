package org.bms.presentation.controllers;

import business.models.User;
import business.services.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashboardController {

    @FXML
    private Label welcomeLabel;

    private User loggedInUser; // Store the logged-in user
    private AccountService accountService;
    private TransactionService transactionService;
    private CustomerService customerService;

    public UserDashboardController() {
        // Initialize your fields, services, etc. here
    }

    public UserDashboardController(AccountService accountService,
                                   TransactionService transactionService,
                                   CustomerService customerService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    public void initializeDashboard(User user) {
        this.loggedInUser = user;
        welcomeLabel.setText("Welcome, " + loggedInUser.getName());
    }

    @FXML
    private void onDepositFunds(ActionEvent event) {
        loadView(event, "/org/bms/presentation/DepositFundsView.fxml");
    }

    @FXML
    private void onLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/bms/presentation/LoginView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onViewAccountDetails(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/bms/presentation/AccountDetailsView.fxml"));
            Parent root = loader.load();

            AccountDetailsViewController controller = loader.getController();
            controller.initData(this.loggedInUser);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadView(ActionEvent event, String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
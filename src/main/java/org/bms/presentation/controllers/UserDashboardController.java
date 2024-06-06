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

//import java.awt.event.MouseEvent;
import java.io.IOException;

public class UserDashboardController {

    @FXML
    private Label welcomeLabel;

    private User loggedInUser; // Store the logged-in user
    // Inject services using your DI framework (e.g., Spring)
    private AccountService accountService;
    private TransactionService transactionService;
    private CustomerService customerService;
    // ... other services

    // Constructor or @Autowired (if using Spring)
    public UserDashboardController(AccountService accountService,
                                   TransactionService transactionService,
                                   CustomerService customerService
                                   // ... other services
                                    ) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.customerService = customerService;
    // ... initialize other services
}

// Method to initialize the dashboard with user data
public void initializeDashboard(User user) {
    this.loggedInUser = user;
    welcomeLabel.setText("Welcome, " + loggedInUser.getName());
}

@FXML
private void onDepositFunds(ActionEvent event) {
    // Load Deposit Funds view
    loadView(event, "DepositFundsView.fxml");
}

// ... (Implement other button handlers similarly)

@FXML
private void onLogout(ActionEvent event) {
    // Handle logout (e.g., clear session, navigate back to log in)
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountDetailsView.fxml"));
            Parent root = loader.load();

            // Get the controller of the loaded view
            AccountDetailsViewController controller = loader.getController();
            // Initialize the controller with the logged-in user
            controller.initData(this.loggedInUser);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
        }
    }

// Helper method to load other views
private void loadView(ActionEvent event, String fxmlFileName) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent root = loader.load();

        // Get the controller of the loaded view (if you need to pass data)
        // MyOtherViewController controller = loader.getController();
        // controller.initData(someData);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // Handle the exception (e.g., show an error message)
    }
}
}
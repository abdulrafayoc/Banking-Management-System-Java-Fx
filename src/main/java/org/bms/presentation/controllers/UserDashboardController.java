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

// Button Action Handlers (Examples)
@FXML
private void onViewAccountDetails(ActionEvent event) {
    // Load Account Details view and pass loggedInUser if needed
    loadView(event, "AccountDetailsView.fxml");
}

@FXML
private void onDepositFunds(ActionEvent event) {
    // Load Deposit Funds view
    loadView(event, "DepositFundsView.fxml");
}

// ... (Implement other button handlers similarly)

@FXML
private void onLogout(ActionEvent event) {
    // Handle logout (e.g., clear session, navigate back to login)
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
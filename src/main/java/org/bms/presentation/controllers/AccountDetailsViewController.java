package org.bms.presentation.controllers;
import business.models.Account;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//import java.awt.event.MouseEvent;
import java.io.IOException;


public class AccountDetailsViewController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField accountType;
    @FXML
    private TextField balanceField;
    @FXML
    private TextField statusField;
    @FXML
    private TextField branchField;

    private User loggedInUser; // Store the logged-in user
    //Add services that could be used in the controller
    private AccountService accountService;
    private TransactionService transactionService;
    private CustomerService customerService;
    // ... other services


    public AccountDetailsViewController() {
        // You can leave this empty, or you can initialize services here
        this.accountService = new AccountService( );
        this.transactionService = new TransactionService();
        this.customerService = new CustomerService();

    }
    // Constructor or @Autowired (if using Spring)
    public AccountDetailsViewController(AccountService accountService,
                                       TransactionService transactionService,
                                       CustomerService customerService
                                       // ... other services
                                        ) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.customerService = customerService;
    // ... initialize other services
}

    public void initData(User user) {
        // Initialize the view with the user data
        this.loggedInUser = user;

        Account account = accountService.getAccountByUserId(user.getUserId());
        // Display the account details
        nameField.setText(user.getName());
        accountType.setText(account.getType().toString());
        balanceField.setText(String.valueOf(account.getBalance()));
        statusField.setText(account.getStatus().toString());
        branchField.setText(account.getBranch().toString());

        // Add logic to display transactions, cards, etc.

        

    }
}


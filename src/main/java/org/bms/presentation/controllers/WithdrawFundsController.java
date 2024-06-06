package org.bms.presentation.controllers;

import business.models.Account;
import business.services.AccountService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WithdrawFundsController {

    @FXML
    private TextField accountField;

    @FXML
    private TextField withdrawAmountField;

    @FXML
    private Label confirmationLabel;

    private AccountService accountService;

    public WithdrawFundsController() {
        // No-argument constructor
    }

    public WithdrawFundsController(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @FXML
    private void onWithdrawButtonClick() {
        // Get the account number from the text field
        String accountNumberText = accountField.getText();
        int accountNumber = Integer.parseInt(accountNumberText);

        // Get the withdrawal amount from the text field
        String withdrawAmountText = withdrawAmountField.getText();
        double withdrawAmount = Double.parseDouble(withdrawAmountText);

        //Calculate the new balance
        double newBalance = withdrawAmount;
        // This will depend on how your accountService.deposit method works
        // For example, if it returns the new balance, you could do:
        // double newBalance = accountService.deposit(accountNumber, depositAmount);

        // Update the UI to reflect the successful deposit
        // This might involve displaying a success message, updating the account balance display, etc.
        confirmationLabel.setText("Deposit successful. Cashed out " + newBalance);

    }
}
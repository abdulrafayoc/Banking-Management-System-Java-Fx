package org.bms.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepositFundsController {

    @FXML
    private TextField depositAmountField;

    @FXML
    private Button depositButton;

    @FXML
    private Label confirmationLabel;

    @FXML
    private void onDepositButtonClick() {

        double depositAmount = Double.parseDouble(depositAmountField.getText());
        double newBalance = depositAmount;
        if (depositAmount <= 0) {
            confirmationLabel.setText("Deposit amount must be positive.");
            return;
        }

        //Calculate the new balance
        // This will depend on how your accountService.deposit method works
        // For example, if it returns the new balance, you could do:
        // double newBalance = accountService.deposit(accountNumber, depositAmount);

        // Update the UI to reflect the successful deposit
        // This might involve displaying a success message, updating the account balance display, etc.
        confirmationLabel.setText("Deposit successful. Balance added " + newBalance);
    }
}
package org.bms.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepositFundsController {

    @FXML
    private TextField accountField;

    @FXML
    private TextField depositAmountField;

    @FXML
    private Button depositButton;

    @FXML
    private Label confirmationLabel;

    @FXML
    private void onDepositButtonClick() {
        // Get the account number and deposit amount from the text fields
        String accountNumber = accountField.getText();
        String depositAmountText = depositAmountField.getText();
        double depositAmount = Double.parseDouble(depositAmountText);

        // Perform the deposit operation
        // This might involve calling a method on a service object, for example
        // accountService.deposit(accountNumber, depositAmount);

        //Calculate the new balance
        double newBalance = depositAmount;
        // This will depend on how your accountService.deposit method works
        // For example, if it returns the new balance, you could do:
        // double newBalance = accountService.deposit(accountNumber, depositAmount);

        // Update the UI to reflect the successful deposit
        // This might involve displaying a success message, updating the account balance display, etc.
        confirmationLabel.setText("Deposit successful. Balance added " + newBalance);
    }
}
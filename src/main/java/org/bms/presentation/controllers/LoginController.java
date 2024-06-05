package org.bms.presentation.controllers;


import business.services.UserService;
import business.models.User;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;


    // Add this default constructor:
    public LoginController() {

    }

    private UserService userService; // Inject this using DI

    // Constructor or @Autowired (if using Spring)
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User authenticatedUser = userService.authenticateUser(username, password);

        if (authenticatedUser != null) {
            try {
                // Load the appropriate dashboard based on user role
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountView.fxml")); // Example
                Parent root = loader.load();

                // ... (Pass any necessary data to the next view)

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                // Handle exception (e.g., show error message)
                e.printStackTrace();
            }
        } else {
            // Handle invalid credentials (e.g., show error message)
        }
    }
}
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
import persistence.repository.UserRepositoryImpl;

import java.io.IOException;
import javafx.scene.control.Alert;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserService userService; // Inject this using DI

    // Add this default constructor:
    public LoginController() {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        this.userService = new UserService(userRepository);
    }


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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Successful");
            alert.setHeaderText(null);
            alert.setContentText("Welcome");
            alert.showAndWait();
            try {
                // Load the appropriate dashboard based on user role
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/bms/presentation/hello-view.fxml"));
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
            // Handle invalid credentials (e.g., show error message)
        }
    }
}
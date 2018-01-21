package com.paintme.controllers;

import com.paintme.domain.models.User;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.security.Hashing;
import com.paintme.services.UserService;
import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SignUpController{

    @Autowired
    @Lazy
    protected StageManager stageManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField loginTextField;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button cancelButton;

    public void signUpButton(ActionEvent actionEvent) throws Exception {

        if (this.loginTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Message");
            alert.setHeaderText("Wrong login!");
            alert.setContentText("Login field cannot be empty.");
            alert.showAndWait();
        }
        else if (this.emailTextField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Message");
            alert.setHeaderText("Wrong email!");
            alert.setContentText("Email field cannot be empty.");
            alert.showAndWait();
        }
        else if (this.passwordField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Message");
            alert.setHeaderText("Wrong password!");
            alert.setContentText("Password field cannot be empty.");
            alert.showAndWait();
        }
        else if (!Objects.equals(this.passwordField.getText(), this.confirmPasswordField.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Message");
            alert.setHeaderText("Wrong confirm password!");
            alert.setContentText("Password and Confirm password fields are different.");
            alert.showAndWait();
        }
        else {

            User userToAdd = new User();
            userToAdd.setLogin(this.loginTextField.getText());
            userToAdd.setPasswordSalt(Hashing.getSalt("SHA1PRNG"));
            userToAdd.setPasswordHash(Hashing.getSecurePassword(
                    this.passwordField.getText(), userToAdd.getPasswordSalt(), "SHA-256"));
            userToAdd.setEmail(this.emailTextField.getText());

            this.userRepository.save(userToAdd);
            this.userService.uploadUser(userToAdd);

            this.stageManager.switchScene(FxmlView.HOMEPAGE);
        }
    }

    public void cancelButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.SIGNIN);
    }
}

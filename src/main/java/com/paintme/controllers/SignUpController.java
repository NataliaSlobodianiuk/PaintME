package com.paintme.controllers;

import com.paintme.domain.models.User;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.security.Hashing;
import com.paintme.services.UserService;
import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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

    public void signUpButton(ActionEvent actionEvent) throws Exception {

        if (this.passwordField.getText() !=  this.confirmPasswordField.getText()) {
            // TODO: 5/26/2017  Allert Popup Password and Confirm password fields are different
        }

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

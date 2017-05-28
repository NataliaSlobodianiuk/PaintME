package com.paintme.controllers;

import com.paintme.domain.models.User;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.security.Hashing;
import com.paintme.services.UserService;
import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SignInController{

    @Autowired
    @Lazy
    protected StageManager stageManager;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @FXML
    private Label loginLabel;

    @FXML
    private TextField loginTextField;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Label signUpLabel;

    @FXML
    private Hyperlink signUpHyperlink;

    public void initialize() {
        this.loginTextField.setText("login");
        this.passwordField.setText("password");
    }

    public void signInButton(ActionEvent actionEvent) throws Exception {
        this.userRepository.findAll();
        User user = this.userRepository.findByLogin(this.loginTextField.getText());

        if (user != null) {
            byte[] salt = user.getPasswordSalt();
            String passwordHash = Hashing.getSecurePassword(
                    this.passwordField.getText(), salt, "SHA-256");

            if (Objects.equals(user.getPasswordHash(), passwordHash)) {
                this.stageManager.switchScene(FxmlView.HOMEPAGE);
            }
        }

        this.userService.loadUser();
    }

    public void signUpHyperlink(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.SIGNUP);
    }
}

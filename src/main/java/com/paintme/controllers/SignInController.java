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
    private Button cancelButton;

    @FXML
    private Label signUpLabel;

    @FXML
    private Hyperlink signUpHyperlink;

    public void initialize() {
        this.loginTextField.setText("login");
        this.passwordField.setText("password");
    }

    public void signInButton(ActionEvent actionEvent) throws Exception {
        User user = this.userRepository.findByLogin(this.loginTextField.getText());

        if (user != null) {
            byte[] salt = user.getPasswordSalt();
            String passwordHash = Hashing.getSecurePassword(
                    this.passwordField.getText(), salt, "SHA-256");

            if (Objects.equals(user.getPasswordHash(), passwordHash)) {
                this.userService.uploadUser(user);
                this.stageManager.switchScene(FxmlView.HOMEPAGE);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert Message");
                alert.setHeaderText("Authentication failed.");
                alert.setContentText("Invalid login or/and password!");
                alert.showAndWait();
            }
        }
    }

    public void cancelButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.MAIN);
    }

    public void signUpHyperlink(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.SIGNUP);
    }
}

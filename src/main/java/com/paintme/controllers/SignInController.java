package com.paintme.controllers;

import com.paintme.domain.repositories.UserRepository;
import com.paintme.services.UserService;
import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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
        boolean success = true;

        try {
            this.userService.setSessionUser(this.loginTextField.getText(), this.passwordField.getText());
        }
        catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Message");
            alert.setHeaderText("Authentication failed.");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();

            success = false;
        }

        if (success) {
            this.stageManager.switchScene(FxmlView.HOMEPAGE);
        }
    }

    public void cancelButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.MAIN);
    }

    public void signUpHyperlink(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.SIGNUP);
    }
}

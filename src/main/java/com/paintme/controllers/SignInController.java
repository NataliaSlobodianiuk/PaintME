package com.paintme.controllers;

import com.paintme.domain.models.User;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.security.Hashing;
import com.paintme.security.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignInController{
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserService userService;

    private UserRepository userRepository;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordField;

    public void signInButton(ActionEvent actionEvent) throws Exception{
        User user = this.userRepository.findByLogin(this.loginTextField.getText());

        if (user != null){
            byte[] salt = user.getPasswordSalt();
            String passwordHash = Hashing.getSecurePassword(
                    this.passwordField.getText(), salt, "SHA-256");

            if (user.getPasswordHash() == passwordHash) {
                /* todo */
            }

        }

        this.userService.loadUser();
    }

    public void signUpHyperlink(ActionEvent actionEvent) throws Exception {

    }

    public void initialize() {
        this.loginTextField.setText("login");
        this.passwordField.setText("password");
    }
}

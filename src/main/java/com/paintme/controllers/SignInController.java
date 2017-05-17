package com.paintme.controllers;

import com.paintme.domain.models.User;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.security.SHA256;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SignInController implements Initializable{
    private UserRepository userRepository;
    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signInButton(ActionEvent actionEvent) throws Exception{
        User user = this.userRepository.findByLogin(this.loginTextField.getText());

        if (user != null){
            byte[] salt = user.getPasswordSalt();
            String passwordHash = SHA256.getSHA256SecurePassword(
                    this.passwordField.getText(), salt, "SHA-256");

            if (user.getPasswordHash() == passwordHash) {
                /* todo */
            }
        }
    }

    public void signUpHyperlink(ActionEvent actionEvent) throws Exception {

    }
}

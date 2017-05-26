package com.paintme.controllers;

import com.paintme.domain.models.User;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.security.Hashing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpController{
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
        User usertoAdd = new User();
        usertoAdd.setLogin("julia");
        usertoAdd.setPasswordSalt(Hashing.getSalt("SHA1PRNG"));
        usertoAdd.setPasswordHash(Hashing.getSecurePassword("mypassword", usertoAdd.getPasswordSalt(), "SHA-256"));
        usertoAdd.setEmail("julia@example.com");
        this.userRepository.save(usertoAdd);

        FXMLLoader fxmlLoader = new FXMLLoader((getClass()
                .getResource("/fxml/homePage.fxml")));

        Parent root = fxmlLoader.load();

        Stage homePageStage = new Stage();
        homePageStage.setTitle("Home Page");
        homePageStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
        homePageStage.setScene(new Scene(root, 450, 350));
        homePageStage.setResizable(false);
        homePageStage.show();
    }
}

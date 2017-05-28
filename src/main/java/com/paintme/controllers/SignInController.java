package com.paintme.controllers;

import com.paintme.PaintMEApplication;
import com.paintme.domain.models.User;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.security.Hashing;
import com.paintme.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SignInController{

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
    private Label signUpLabel;

    @FXML
    private Hyperlink signUpHyperlink;

    public void signInButton(ActionEvent actionEvent) throws Exception {
        User user = this.userRepository.findByLogin(this.loginTextField.getText());

        if (user != null) {
            byte[] salt = user.getPasswordSalt();
            String passwordHash = Hashing.getSecurePassword(
                    this.passwordField.getText(), salt, "SHA-256");

            if (Objects.equals(user.getPasswordHash(), passwordHash)) {
                this.userService.uploadUser(user);

                FXMLLoader fxmlLoader = new FXMLLoader(
                        getClass().getResource("/fxml/homePage.fxml"));
                fxmlLoader.setControllerFactory(PaintMEApplication.springContext::getBean);
                Parent root = fxmlLoader.load();

                Stage homePageStage = new Stage();
                homePageStage.setTitle("Home Page");
                homePageStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
                root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
                homePageStage.setScene(new Scene(root, 450, 350));
                homePageStage.setResizable(false);
                homePageStage.show();
            }
            else {
                /// TODO: 5/26/2017  Allert Popup Wrong login or/and password
            }
        }
    }

    public void signUpHyperlink(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/fxml/signUp.fxml"));
        fxmlLoader.setControllerFactory(PaintMEApplication.springContext::getBean);
        Parent root = fxmlLoader.load();

        Stage signUpStage = new Stage();
        signUpStage.setTitle("Sign Up");
        signUpStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
        signUpStage.setScene(new Scene(root, 350, 350));
        signUpStage.setResizable(false);
        signUpStage.show();
    }

    public void initialize() {
        this.loginTextField.setText("login");
        this.passwordField.setText("password");
    }
}

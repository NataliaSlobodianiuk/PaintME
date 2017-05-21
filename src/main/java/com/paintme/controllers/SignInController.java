package com.paintme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class SignInController{
    public void initialize(){
    }

    public void signInButton(ActionEvent actionEvent) throws Exception{
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

    public void signUpHyperlink(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/signUp.fxml"));

        Parent root = fxmlLoader.load();

        Stage signUpStage = new Stage();
        signUpStage.setTitle("Sign Up");
        signUpStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
        signUpStage.setScene(new Scene(root, 350, 350));
        signUpStage.setResizable(false);
        signUpStage.show();
    }
}

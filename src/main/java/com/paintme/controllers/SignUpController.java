package com.paintme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

public class SignUpController{
    public void initialize(){
    }

    public void signUpButton(ActionEvent actionEvent) throws Exception {
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

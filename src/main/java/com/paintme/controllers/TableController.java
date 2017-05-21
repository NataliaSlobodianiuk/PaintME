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

public class TableController{
    public void initialize(){
    }

    public void cancelTableButton(ActionEvent actionEvent) throws Exception {

    }

    public void applyTableButton(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/game.fxml"));

        Parent root = fxmlLoader.load();

        Stage gameStage = new Stage();
        gameStage.setTitle("Game");
        gameStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
        gameStage.setScene(new Scene(root, 600, 350));
        gameStage.setResizable(false);
        gameStage.show();
    }
}

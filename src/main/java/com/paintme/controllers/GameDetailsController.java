package com.paintme.controllers;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class GameDetailsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    @FXML
    private GridPane root;

    public void cancelGameDetails(){

    }

    public void applyGameDetailsButton(ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/game.fxml"));

        Parent root = fxmlLoader.load();

        Stage gameDetailsStage = new Stage();
        gameDetailsStage.setTitle("Game");
        gameDetailsStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        gameDetailsStage.setScene(new Scene(root, 550, 350));
        gameDetailsStage.setResizable(false);
        gameDetailsStage.show();
    }
}
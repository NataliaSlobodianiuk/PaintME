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

public class HomePageController{
    public void initialize(){
    }

    public void createTabelButton(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/table.fxml"));

        Parent root = fxmlLoader.load();

        Stage gameDetailsStage = new Stage();
        gameDetailsStage.setTitle("Table");
        gameDetailsStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
        gameDetailsStage.setScene(new Scene(root, 450, 350));
        gameDetailsStage.setResizable(false);
        gameDetailsStage.show();
    }

    public void leaderboardTabelButton(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/leaderBoard.fxml"));

        Parent root = fxmlLoader.load();

        Stage gameDetailsStage = new Stage();
        gameDetailsStage.setTitle("Leaderboard");
        gameDetailsStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
        gameDetailsStage.setScene(new Scene(root, 550, 350));
        gameDetailsStage.setResizable(false);
        gameDetailsStage.show();
    }

    public void joinGameButton(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/game.fxml"));

        Parent root = fxmlLoader.load();

        Stage gameDetailsStage = new Stage();
        gameDetailsStage.setTitle("Game");
        gameDetailsStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
        gameDetailsStage.setScene(new Scene(root, 600, 350));
        gameDetailsStage.setResizable(false);
        gameDetailsStage.show();
    }
}

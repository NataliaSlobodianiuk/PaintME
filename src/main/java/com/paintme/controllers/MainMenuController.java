package com.paintme.controllers;

import com.paintme.domain.repositories.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController{
    @Autowired
    private UserRepository repository;

    public void playComputerModeButton(ActionEvent actionEvent) throws Exception {
        this.repository.findAll();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/gameDetails.fxml"));

        Parent root = fxmlLoader.load();

        Stage gameDetailsStage = new Stage();
        gameDetailsStage.setTitle("Game Details");
        gameDetailsStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        gameDetailsStage.setScene(new Scene(root, 450, 350));
        gameDetailsStage.setResizable(false);
        gameDetailsStage.show();
    }

    public void play2PlayersModeButton(ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/gameDetails.fxml"));

        Parent root = fxmlLoader.load();

        Stage gameDetailsStage = new Stage();
        gameDetailsStage.setTitle("Game Details");
        gameDetailsStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        gameDetailsStage.setScene(new Scene(root, 450, 350));
        gameDetailsStage.setResizable(false);
        gameDetailsStage.show();
    }

    public void playOnlineModeButton(ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/signIn.fxml"));

        Parent root = fxmlLoader.load();

        Stage signInStage = new Stage();
        signInStage.setTitle("Sign In");
        signInStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        signInStage.setScene(new Scene(root, 450, 350));
        signInStage.setResizable(false);
        signInStage.show();
    }
}

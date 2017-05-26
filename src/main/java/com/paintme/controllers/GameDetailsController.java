package com.paintme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class GameDetailsController{
    @FXML
    private Label player1LoginLabel;

    @FXML
    private TextField player1LoginTextField;

    @FXML
    private Label player2LoginLabel;

    @FXML
    private TextField player2LoginTextField;

    @FXML
    private Label color1Label;

    @FXML
    private ColorPicker colorPicker1;

    @FXML
    private Label color2Label;

    @FXML
    private ColorPicker colorPicker2;

    @FXML
    private Label difficultyLabel;

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private Button applyButton;

    @FXML
    private Button cancelButton;

    public void cancelGameDetails(){
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

    public void applyGameDetailsButton(ActionEvent actionEvent) throws Exception{
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
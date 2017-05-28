package com.paintme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class TableController {

    @FXML
    private Label timeLabel;

    @FXML
    private ComboBox<String> timeComboBox;

    @FXML
    private Label cubeSizeLabel;

    @FXML
    private ComboBox<String> cubeSizeComboBox;

    @FXML
    private Label teamsLabel;

    @FXML
    private ComboBox<String> teamsComboBox;

    @FXML
    private Label playersLabel;

    @FXML
    private ComboBox<String> playersComboBox;

    @FXML
    private Button applyButton;

    @FXML
    private Button cancelButton;

    public void initialize() {
    }

    public void cancelTableButton(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
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

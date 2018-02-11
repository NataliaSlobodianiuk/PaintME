package com.paintme.controllers;

import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class GameDetailsController{

    @Autowired
    @Lazy
    protected StageManager stageManager;

    @FXML
    private Button backButton;

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
    private Button resetButton;

    public void initialize() {
        this.player2LoginLabel.visibleProperty().setValue(false);
        this.player2LoginTextField.visibleProperty().setValue(false);

        this.colorPicker2.visibleProperty().setValue(false);
        this.color2Label.visibleProperty().setValue(false);
    }

    public void backToMainMenuButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.MAIN);
    }

    public void resetGameDetailsButton(ActionEvent actionEvent) throws Exception {
        //this.stageManager.switchScene(FxmlView.MAIN);
    }

    public void applyGameDetailsButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.GAME);
    }
}
package com.paintme.controllers;

import com.paintme.PaintMEException;
import com.paintme.controllers.Helpers.Alerts;
import com.paintme.infrastucture.GameMode;
import com.paintme.services.GameService;
import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class GameDetailsController{

    //region Managers and Services
    @Autowired
    @Lazy
    protected StageManager stageManager;

    @Autowired
    private GameService gameService;
    //endregion

    //region FXML Fields
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
    //endregion

    //region Fields
    private GameMode gameMode;
    //endregion

    public void initialize() {
        try {
            this.gameMode = this.gameService.getGameMode();
        } catch (PaintMEException e) {
            Alerts.showGamePropertiesAlert(e.getMessage());
        }

        if (this.gameMode == GameMode.COMPUTER) {
            this.player1LoginTextField.setText("player");

            this.player2LoginLabel.visibleProperty().setValue(false);
            this.player2LoginTextField.visibleProperty().setValue(false);

            this.colorPicker2.visibleProperty().setValue(false);
            this.color2Label.visibleProperty().setValue(false);
        }
        else {
            this.player1LoginTextField.setText("player1");
            this.player2LoginTextField.setText("player2");

            this.difficultyLabel.visibleProperty().setValue(false);
            this.difficultyComboBox.visibleProperty().setValue(false);
        }
    }

    public void backToMainMenuButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.MAIN);
    }

    public void resetGameDetailsButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.GAMEDETAILS);
    }

    public void applyGameDetailsButton(ActionEvent actionEvent) throws Exception {
        if (this.isInputCorrect()) {
            this.gameService.setSide1Login(this.player1LoginTextField.getText());
            this.gameService.setSide1Color("#" + this.colorPicker1.getValue().toString().substring(2));

            if (this.gameMode == GameMode.COMPUTER) {
                this.gameService.setDifficulty(this.difficultyComboBox.getValue().toUpperCase());
            } else {
                this.gameService.setSide2Login(this.player2LoginTextField.getText());
                this.gameService.setSide2Color("#" + this.colorPicker2.getValue().toString().substring(2));
            }

            this.stageManager.switchScene(FxmlView.GAME);
        }
    }

    private boolean isInputCorrect() {
        boolean isCorrect = true;

        if (this.player1LoginTextField.getText().isEmpty()){
            Alerts.showWrongInputAlert("Login field (" + this.player1LoginLabel.getText() + ") cannot be empty.");
            isCorrect = false;
        }

        if (this.gameMode == GameMode.TWOPLAYERS) {
            if (this.player2LoginTextField.getText().isEmpty()){
                Alerts.showWrongInputAlert("Login field (" + this.player2LoginLabel.getText() + ") cannot be empty.");
                isCorrect = false;
            }
        }

        return isCorrect;
    }
}
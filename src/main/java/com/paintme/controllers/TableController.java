package com.paintme.controllers;

import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TableController {

    @Autowired
    @Lazy
    protected StageManager stageManager;

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

    public void cancelTableButton(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

    public void applyTableButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.GAME);
    }
}

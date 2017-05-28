package com.paintme.controllers;

import com.paintme.domain.models.GameTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.shape.Box;
import org.springframework.stereotype.Component;

@Component
public class GameController {

    private GameTable table;

    @FXML
    private ListView<String> team1ListView;

    @FXML
    private ListView<String> team2ListView;

    @FXML
    private Label timeLabel;

    @FXML
    private Box cube;

    @FXML
    private PerspectiveCamera boxCamera;

    public void initialize() {

    }

    public void cell (ActionEvent actionEvent) throws Exception {
        Button ccell = (Button)actionEvent.getSource();

        int cellNumber = Integer.parseInt(ccell.getId());

        ccell.setStyle("-fx-base: #b6e7c9");
    }

}

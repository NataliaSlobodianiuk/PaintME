package com.paintme.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

@Component
public class LeaderboardController {

    @FXML
    private TableView<TableColumn> leaderboard;

    public void initialize() {
    }
}

package com.paintme.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.TableColumn;
import org.springframework.stereotype.Component;

import javax.swing.table.*;

@Component
public class LeaderboardController {

    @FXML
    private TableView<TableColumn> leaderboard;

    public void initialize() {
    }
}

package com.paintme.controllers;

import com.paintme.domain.repositories.UserRepository;
import com.paintme.view.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.TableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.swing.table.*;

@Component
public class LeaderboardController {

    @Autowired
    @Lazy
    protected StageManager stageManager;

    @FXML
    private TableView<TableColumn> leaderboard;
}

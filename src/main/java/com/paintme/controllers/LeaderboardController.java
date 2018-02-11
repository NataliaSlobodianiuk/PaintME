package com.paintme.controllers;

import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LeaderboardController {

    @Autowired
    @Lazy
    protected StageManager stageManager;

    @FXML
    private Button backButton;

    @FXML
    private TableView<TableColumn> leaderboard;

    public void backToHomePageButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.HOMEPAGE);
    }
}

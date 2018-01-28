package com.paintme.controllers;

import com.paintme.services.UserService;
import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class HomePageController {

    @Autowired
    @Lazy
    protected StageManager stageManager;

    @Autowired
    private UserService userService;

    @FXML
    private Label loginLabel;

    @FXML
    private ListView<String> tablesLListView;

    @FXML
    private Button joinTableButton;

    @FXML
    private Button createTableButton;

    @FXML
    private Button leaderboardButton;

    @FXML
    private Button signOutButton;

    public void createTabelButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.TABLE);
    }

    public void leaderboardTabelButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.LEADERBOARD);
    }

    public void joinGameButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.GAME);
    }

    public void signOutButton(ActionEvent actionEvent) throws Exception {
        this.userService.removeSessionUser();
        this.stageManager.switchScene(FxmlView.SIGNIN);
    }
}

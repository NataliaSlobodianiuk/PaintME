package com.paintme.controllers;

import com.paintme.PaintMEException;
import com.paintme.controllers.Helpers.Alerts;
import com.paintme.domain.models.User;
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

    //region FXML Fields
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
    //endregion

    public void initialize(){
        User sessionUser = null;

        try {
            sessionUser = this.userService.getSessionUser();
        } catch (PaintMEException e) {
            Alerts.showUnableToGetSessionUserAlert(e.getMessage());
        }

        if (sessionUser != null) {
            this.loginLabel.setText(sessionUser.getLogin());
        }
    }

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

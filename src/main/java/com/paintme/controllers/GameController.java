package com.paintme.controllers;

import com.paintme.domain.models.GameTable;
import com.paintme.domain.models.User;
import com.paintme.services.UserService;
import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.shape.Box;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class GameController {

    @Autowired
    @Lazy
    protected StageManager stageManager;

    @Autowired
    private UserService userService;

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

    @FXML
    private Button exitButton;

    public void cell (ActionEvent actionEvent) throws Exception {
        Button ccell = (Button)actionEvent.getSource();

        int cellNumber = Integer.parseInt(ccell.getId());

        ccell.setStyle("-fx-base: #b6e7c9");
    }

    public void exitButton(ActionEvent actionEvent) throws Exception{
        User user = this.userService.getSessionUser();

        if (user == null) {
            this.stageManager.switchScene(FxmlView.MAIN);
        }
        else{
            this.stageManager.switchScene(FxmlView.HOMEPAGE);
        }
    }

}

package com.paintme.controllers;

import com.paintme.domain.models.User;
import com.paintme.services.UserService;
import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController{

    @Autowired
    @Lazy
    protected StageManager stageManager;

    @Autowired
    private UserService userService;

    @FXML
    private Label paintMeLabel;

    @FXML
    private Image image;

    @FXML
    private Button computerButton;

    @FXML
    private Button twoPlayersButton;

    @FXML
    private Button onlineButton;

    public void playComputerModeButton(ActionEvent actionEvent) throws Exception {
        this.stageManager.switchScene(FxmlView.GAMEDETAILS);
    }

    public void play2PlayersModeButton(ActionEvent actionEvent) throws Exception{
        this.stageManager.switchScene(FxmlView.GAMEDETAILS);
    }

    public void playOnlineModeButton(ActionEvent actionEvent) throws Exception{
        User user = this.userService.getSessionUser();

        if (user == null) {
            this.stageManager.switchScene(FxmlView.SIGNIN);
        }
        else {
            this.stageManager.switchScene(FxmlView.HOMEPAGE);
        }
    }
}

package com.paintme.controllers;

import com.paintme.domain.repositories.UserRepository;
import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController{

    @Autowired
    @Lazy
    protected StageManager stageManager;

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
        this.stageManager.switchScene(FxmlView.SIGNIN);
    }
}

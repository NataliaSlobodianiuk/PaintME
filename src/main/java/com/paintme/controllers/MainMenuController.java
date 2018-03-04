package com.paintme.controllers;

import com.paintme.PaintMEException;
import com.paintme.domain.models.User;
import com.paintme.domain.models.statuses.UserStatuses;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.infrastucture.GameMode;
import com.paintme.services.GameService;
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

    //region Managers and Services
    @Autowired
    @Lazy
    protected StageManager stageManager;

    @Autowired
    private UserService userService;

    @Autowired
    private GameService gameService;

    @Autowired
    private UserRepository userRepository;
    //endregion

    //region FXML Fields
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
    //endregion

    public void playComputerModeButton(ActionEvent actionEvent) throws Exception {
        this.gameService.setGameMode(GameMode.COMPUTER);
        this.stageManager.switchScene(FxmlView.GAMEDETAILS);
    }

    public void play2PlayersModeButton(ActionEvent actionEvent) throws Exception{
        this.gameService.setGameMode(GameMode.TWOPLAYERS);
        this.stageManager.switchScene(FxmlView.GAMEDETAILS);
    }

    public void playOnlineModeButton(ActionEvent actionEvent) throws Exception{
        this.gameService.setGameMode(GameMode.MULTIPLAYER);
        User user = this.userService.getSessionUser();

        if (user == null) {
            this.stageManager.switchScene(FxmlView.SIGNIN);
        }
        else {
            if (user.getStatus() != UserStatuses.OFFLINE){
                throw new PaintMEException(
                        "User with login " + user.getLogin() +
                                " and password hash " + user.getPasswordHash() +
                                " is already signed in.");
            }
            else {
                user.setStatus(UserStatuses.ONLINE);
                this.userRepository.save(user);

                this.stageManager.switchScene(FxmlView.HOMEPAGE);
            }
        }
    }
}

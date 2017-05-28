package com.paintme.controllers;

import com.paintme.domain.repositories.UserRepository;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class GameController {

    @Autowired
    @Lazy
    protected StageManager stageManager;

    @FXML
    private Image side1;

    @FXML
    private Image side2;

    @FXML
    private Image side3;

    @FXML
    private Image side4;

    @FXML
    private Image side5;

    @FXML
    private Image side6;

    @FXML
    private ListView<String> team1ListView;

    @FXML
    private ListView<String> team2ListView;

    @FXML
    private Label timeLabel;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Box cube;

    @FXML
    private PerspectiveCamera boxCamera;

    public void button1 (ActionEvent actionEvent) throws Exception {

    }

    public void button2 (ActionEvent actionEvent) throws Exception {

    }

    public void button3 (ActionEvent actionEvent) throws Exception {

    }

    public void button4 (ActionEvent actionEvent) throws Exception {

    }

    public void button5 (ActionEvent actionEvent) throws Exception {

    }

    public void button6 (ActionEvent actionEvent) throws Exception {

    }

    public void button7 (ActionEvent actionEvent) throws Exception {

    }

    public void button8 (ActionEvent actionEvent) throws Exception {

    }

    public void button9 (ActionEvent actionEvent) throws Exception {

    }
}

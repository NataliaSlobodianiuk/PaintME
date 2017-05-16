package com.paintme.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class GameController implements Initializable{
    @FXML
    private ListView<String> team1ListView;

    @FXML
    private ListView<String> team2ListView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        team1ListView.getItems().add("hbj");
    }

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

package com.paintme.controllers;

import com.paintme.domain.models.Board;
import com.paintme.domain.models.GameTable;
import com.paintme.domain.models.Team;
import com.paintme.domain.models.User;
import com.paintme.infrastucture.board_examiners.BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square3BoardExaminer;
import com.paintme.infrastucture.strategies.EasyMode;
import com.paintme.infrastucture.strategies.GameDifficultyStrategy;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.shape.Box;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class GameController {

    private boolean isToMove;
    private int numToMove;

    private User player1;
    private User player2;

    private Team team1;
    private Team team2;

    private GameDifficultyStrategy strategy;
    private BoardExaminer examiner;

    private Board board;

    public void initialize() {
        this.isToMove = true;
        this.numToMove = 1;

        this.player1 = new User();
        this.player1.setLogin("player1");

        this.team1 = new Team();
        this.team1.setColor(1);
        this.team1.setRgb("#b6e7c9");

        this.player2 = new User();
        this.player2.setLogin("computer");

        this.team2 = new Team();
        this.team2.setColor(2);
        this.team2.setRgb("#cd41e3");

        this.strategy = new EasyMode();
        this.examiner = new Square3BoardExaminer();

        this.board = new Board();
        board.setCells("---------");

        this.team1ListView.getItems().add(this.player1.getLogin());
        this.team2ListView.getItems().add(this.player2.getLogin());
    }

    @Autowired
    @Lazy
    protected StageManager stageManager;

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

    public void cell (ActionEvent actionEvent) throws Exception {
        if (this.isToMove) {
            String cells = this.board.getCells();

            Button ccell = (Button) actionEvent.getSource();

            int cellNumber = Integer.parseInt(ccell.getId());

            if (cells.charAt(cellNumber) != '-') {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert Message");
                alert.setHeaderText("Not A Free Cell");
                alert.setContentText("This cell is already painted, choose the other one!");
                alert.showAndWait();
                return;
            }

            if (this.numToMove == 1)
            {
                ccell.setStyle("-fx-base: " + this.team1.getRgb());
                char[] cellsArr = cells.toCharArray();
                cellsArr[cellNumber] = this.team1.getColor().toString().charAt(0);
                cells = String.valueOf(cellsArr);
                this.board.setCells(cells);
                this.numToMove = 2;

            }
            else if (this.numToMove == 2)
            {
                ccell.setStyle("-fx-base: " + this.team2.getRgb());
                char[] cellsArr = cells.toCharArray();
                cellsArr[cellNumber] = this.team2.getColor().toString().charAt(0);
                cells = String.valueOf(cellsArr);
                this.board.setCells(cells);
                this.numToMove = 1;
            }

            if (this.examiner.isFinished(this.board.getCells())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("End Game Message");
                if (this.examiner.findWinningSymbol(this.board.getCells()) == '-') {
                    alert.setHeaderText("Draw");
                    alert.setContentText("It's a draw!");
                }
                else if (this.examiner.findWinningSymbol(
                        this.board.getCells()) == this.team1.getColor()) {
                    alert.setHeaderText("Win");
                    alert.setContentText(this.player1.getLogin() + " wins! Congrats!");
                }
                else if (this.examiner.findWinningSymbol(
                        this.board.getCells()) == this.team2.getColor()) {
                    alert.setHeaderText("Win");
                    alert.setContentText(this.player2.getLogin() + " wins! Congrats!");
                }
                alert.showAndWait();
                return;
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert Message");
            alert.setHeaderText("Not Your Turn");
            alert.setContentText("It is not your turn. Wait for the opponent!");
            alert.showAndWait();
            return;
        }
    }

    private int findAFreeCell(String cells){
        for (int i = 0; i < cells.length(); i++) {
            if (cells.charAt(i) == '-') {
                return i;
            }
        }
        return -1;
    }
}

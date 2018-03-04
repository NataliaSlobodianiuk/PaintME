package com.paintme.controllers;

import com.paintme.domain.models.Board;
import com.paintme.domain.models.GameTable;
import com.paintme.domain.models.Team;
import com.paintme.domain.models.User;
import com.paintme.infrastucture.board_examiners.BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square3BoardExaminer;
import com.paintme.infrastucture.strategies.EasyMode;
import com.paintme.infrastucture.strategies.GameDifficultyStrategy;
import com.paintme.services.UserService;
import com.paintme.view.FxmlView;
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

    //region Fields
    private boolean isToMove;
    private int numToMove;

    private Team team1;
    private Team team2;

    private User player1;
    private User player2;

    private GameDifficultyStrategy strategy;
    private BoardExaminer examiner;

    private Board board;

    private GameTable table;

    //endregion

    //region FXML Fields

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

    //endregion

    //region Managers and Services

    @Autowired
    @Lazy
    protected StageManager stageManager;

    @Autowired
    private UserService userService;

    //endregion

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

    public void cell (ActionEvent actionEvent) throws Exception {
        String cells = this.board.getCells();

        if (this.isToMove) {
            Button ccell = (Button) actionEvent.getSource();

            int cellNumber = Integer.parseInt(ccell.getId());

            if (cells.charAt(cellNumber) != '-') {
                this.showNotAFreeCell();
                return;
            }

            if (this.numToMove == 1) {
                ccell.setStyle("-fx-base: " + this.team1.getRgb());
                char[] cellsArr = cells.toCharArray();
                cellsArr[cellNumber] = this.team1.getColor().toString().charAt(0);
                cells = String.valueOf(cellsArr);
                this.board.setCells(cells);
                this.numToMove = 2;

                if (this.examiner.isFinished(cells)) {
                    this.showEndGameMessage();
                    this.exitButton(new ActionEvent());
                    return;
                }

                if (this.strategy != null) {
                    cellNumber = this.strategy.getCellToMark(this.team2.getColor().toString().charAt(0), "SQUARE", cells);
                    cellsArr = cells.toCharArray();
                    cellsArr[cellNumber] = this.team2.getColor().toString().charAt(0);
                    cells = String.valueOf(cellsArr);
                    this.board.setCells(cells);

                    /*
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
                    Parent root = loader.load();
                    ccell = (Button) loader.getNamespace().get(String.valueOf(cellNumber));
                    ccell.setStyle("-fx-base: " + this.team2.getRgb());
                    */

                    this.numToMove = 1;
                }
            }
            else if (this.numToMove == 2) {
                ccell.setStyle("-fx-base: " + this.team2.getRgb());
                char[] cellsArr = cells.toCharArray();
                cellsArr[cellNumber] = this.team2.getColor().toString().charAt(0);
                cells = String.valueOf(cellsArr);
                this.board.setCells(cells);
                this.numToMove = 1;
            }

            if (this.examiner.isFinished(cells)) {
                this.showEndGameMessage();
                this.exitButton(new ActionEvent());
                return;
            }
        }
        else {
            this.showNotYourTurn();
            return;
        }
    }

    private void showNotAFreeCell(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert Message");
        alert.setHeaderText("Not A Free Cell");
        alert.setContentText("This cell is already painted, choose the other one!");
        alert.showAndWait();
    }

    private void showEndGameMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game Message");
        if (this.examiner.findWinningSymbol(this.board.getCells()) == '-') {
            alert.setHeaderText("Draw");
            alert.setContentText("It's a draw!");
        }
        else if (this.examiner.findWinningSymbol(this.board.getCells()) == this.team1.getColor().toString().charAt(0)) {
            alert.setHeaderText("Win");
            alert.setContentText(this.player1.getLogin() + " wins! Congrats!");
        }
        else if (this.examiner.findWinningSymbol(this.board.getCells()) == this.team2.getColor().toString().charAt(0)) {
            alert.setHeaderText("Win");
            alert.setContentText(this.player2.getLogin() + " wins! Congrats!");
        }
        alert.showAndWait();
    }

    private void showNotYourTurn(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert Message");
        alert.setHeaderText("Not Your Turn");
        alert.setContentText("It is not your turn. Wait for the opponent!");
        alert.showAndWait();
    }

    public void exitButton(ActionEvent actionEvent) throws Exception{
        if (this.userService.getSessionUser() == null) {
            this.stageManager.switchScene(FxmlView.MAIN);
        }
        else{
            this.stageManager.switchScene(FxmlView.HOMEPAGE);
        }
    }
}

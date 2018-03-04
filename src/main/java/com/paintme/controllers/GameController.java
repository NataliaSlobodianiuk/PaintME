package com.paintme.controllers;

import com.paintme.PaintMEException;
import com.paintme.controllers.Helpers.Alerts;
import com.paintme.domain.models.Board;
import com.paintme.infrastucture.Difficulty;
import com.paintme.infrastucture.GameMode;
import com.paintme.infrastucture.board_examiners.BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square3BoardExaminer;
import com.paintme.infrastucture.strategies.GameDifficultyStrategy;
import com.paintme.services.GameService;
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

    //region Fields
    private boolean isToMove;
    private int numToMove;

    private String player1Login;
    private String player2Login;

    private String team1RGB;
    private String team2RGB;
    private char team1Color;
    private char team2Color;

    private GameDifficultyStrategy strategy;

    private Board board;
    private BoardExaminer examiner;

    private GameMode gameMode;
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

    @Autowired
    private GameService gameService;
    //endregion

    public void initialize() {
        try {
            this.gameMode = this.gameService.getGameMode();

            this.isToMove = true;
            this.numToMove = 1;

            this.board = new Board();
            board.setCells("---------");
            this.examiner = new Square3BoardExaminer();

            this.team1Color = '1';
            this.team2Color = '2';

            this.player1Login = this.gameService.getSide1Login();
            this.team1RGB = this.gameService.getSide1Color();

            if (this.gameMode == GameMode.COMPUTER) {
                Difficulty difficulty = this.gameService.getDifficulty();
                this.player2Login = "computer (" + difficulty.name() + ")";

                this.strategy = difficulty.toStrategy();
            } else if (this.gameMode == GameMode.TWOPLAYERS) {
                this.player2Login = this.gameService.getSide2Login();
                this.team2RGB = this.gameService.getSide2Color();
            }

            this.team1ListView.getItems().add(this.player1Login);
            this.team2ListView.getItems().add(this.player2Login);
        } catch (PaintMEException e) {
            Alerts.showGamePropertiesAlert(e.getMessage());
        }
    }

    public void cell (ActionEvent actionEvent) throws Exception {
        String cells = this.board.getCells();

        if (this.isToMove) {
            Button ccell = (Button) actionEvent.getSource();
            int cellNumber = Integer.parseInt(ccell.getId());

            if (cells.charAt(cellNumber) != '-') {
                Alerts.showNotAFreeCellAlert();
                return;
            }

            if (this.numToMove == 1) {
                ccell.setStyle("-fx-base: " + this.team1RGB);
                char[] cellsArr = cells.toCharArray();
                cellsArr[cellNumber] = this.team1Color;
                cells = String.valueOf(cellsArr);
                this.board.setCells(cells);
                this.numToMove = 2;

                if (this.examiner.isFinished(cells)) {
                    this.showEndGameAlert();
                    this.exitButton(null);
                    return;
                }

                if (this.strategy != null) {
                    cellNumber = this.strategy.getCellToMark(this.team2Color, "SQUARE", cells);
                    cellsArr = cells.toCharArray();
                    cellsArr[cellNumber] = this.team2Color;
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
                ccell.setStyle("-fx-base: " + this.team2RGB);
                char[] cellsArr = cells.toCharArray();
                cellsArr[cellNumber] = this.team2Color;
                cells = String.valueOf(cellsArr);
                this.board.setCells(cells);
                this.numToMove = 1;
            }

            if (this.examiner.isFinished(cells)) {
                this.showEndGameAlert();
                this.exitButton(new ActionEvent());
                return;
            }
        }
        else {
            Alerts.showNotYourTurnAlert();
            return;
        }
    }

    public void showEndGameAlert() {
        if (this.examiner.findWinningSymbol(this.board.getCells()) == '-') {
            Alerts.showEndGameDrawAlert();
        }
        else if (this.examiner.findWinningSymbol(this.board.getCells()) == this.team1Color) {
            Alerts.showEndGameWinnerAlert(this.player1Login);
        }
        else if (this.examiner.findWinningSymbol(this.board.getCells()) == this.team2Color) {
            Alerts.showEndGameLoserAlert(this.player1Login);
        }
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

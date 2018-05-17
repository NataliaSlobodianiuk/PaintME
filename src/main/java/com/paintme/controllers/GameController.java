package com.paintme.controllers;

import com.paintme.PaintMEException;
import com.paintme.controllers.Helpers.Alerts;
import com.paintme.domain.models.Board;
import com.paintme.infrastucture.BoardType;
import com.paintme.infrastucture.Difficulty;
import com.paintme.infrastucture.Field;
import com.paintme.infrastucture.GameMode;
import com.paintme.infrastucture.board_examiners.*;
import com.paintme.infrastucture.strategies.GameDifficultyStrategy;
import com.paintme.services.GameService;
import com.paintme.services.UserService;
import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

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

    private BoardExaminer examiner;

    private Board board;

    private GameMode gameMode;
    private BoardType boardType;
    private Field boardField;

    private List<Button> buttonsList;

    private Group cube;
    //endregion

    //region FXML Fields
    @FXML
    private GridPane grid;

    @FXML
    private ListView<String> team1ListView;

    @FXML
    private ListView<String> team2ListView;

    @FXML
    private VBox fieldVBox;

    @FXML
    private HBox buttonsHBox;

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
            this.boardType = this.gameService.getBoardType();
            this.boardField = this.gameService.getBoardField();

            this.isToMove = true;
            this.numToMove = 1;

            this.initializeBoard();
            this.initializeTeams();
        } catch (PaintMEException e) {
            Alerts.showGamePropertiesAlert(e.getMessage());
        }
    }

    public void cell (ActionEvent actionEvent) {
        try {
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
                        return;
                    }

                    if (this.gameMode == GameMode.COMPUTER) {
                        cellNumber = this.strategy.getCellToMark(this.team2Color, this.boardType, this.boardField, cells);
                        cellsArr = cells.toCharArray();
                        cellsArr[cellNumber] = this.team2Color;
                        cells = String.valueOf(cellsArr);
                        this.board.setCells(cells);

                        ccell = this.buttonsList.get(cellNumber);
                        ccell.setStyle("-fx-base: " + this.team2RGB);

                        this.numToMove = 1;
                    }
                } else if (this.numToMove == 2) {
                    ccell.setStyle("-fx-base: " + this.team2RGB);
                    char[] cellsArr = cells.toCharArray();
                    cellsArr[cellNumber] = this.team2Color;
                    cells = String.valueOf(cellsArr);
                    this.board.setCells(cells);
                    this.numToMove = 1;
                }

                if (this.examiner.isFinished(cells)) {
                    this.showEndGameAlert();
                }
            } else {
                Alerts.showNotYourTurnAlert();
            }
        } catch (Exception e) {
            Alerts.showGamePropertiesAlert(e.getMessage());
        }
    }

    public void playAgainButton(ActionEvent actionEvent) {
        this.stageManager.switchScene(FxmlView.GAME);
    }

    public void exitButton(ActionEvent actionEvent) throws Exception{
        if (this.userService.getSessionUser() == null) {
            this.stageManager.switchScene(FxmlView.GAMEDETAILS);
        }
        else{
            this.stageManager.switchScene(FxmlView.HOMEPAGE);
        }
    }

    private void initializeBoard(){
        this.board = new Board();

        switch (this.boardType) {
            case _2D:
                switch (this.boardField) {
                    case THREE_BY_THREE:
                        this.setButtonsGridPane(3, 3);
                        board.setCells(new String(new char[9]).replace("\0", "-"));
                        this.examiner = new Square3BoardExaminer();
                        break;
                    case FIVE_BY_FIVE:
                        this.setButtonsGridPane(5, 5);
                        board.setCells(new String(new char[25]).replace("\0", "-"));
                        this.examiner = new Square5BoardExaminer();
                        break;
                    case NINE_BY_NINE:
                        this.setButtonsGridPane(9, 9);
                        board.setCells(new String(new char[81]).replace("\0", "-"));
                        this.examiner = new Square9BoardExaminer();
                }
                break;
            case CUBE:
                switch (this.boardField) {
                    case THREE_BY_THREE:
                        this.setupCube(3, 3);
                        board.setCells(new String(new char[6 * 9]).replace("\0", "-"));
                        this.examiner = new Cube3BoardExaminer();
                        break;
                    case FIVE_BY_FIVE:
                        this.setupCube(5, 5);
                        board.setCells(new String(new char[6 * 25]).replace("\0", "-"));
                        this.examiner = new Cube5BoardExaminer();
                        break;
                    case NINE_BY_NINE:
                        this.setupCube(9, 9);
                        board.setCells(new String(new char[6 * 81]).replace("\0", "-"));
                        this.examiner = new Cube9BoardExaminer();
                }
        }
    }

    private void initializeTeams() throws PaintMEException {
        this.team1Color = '1';
        this.team2Color = '2';

        this.player1Login = this.gameService.getSide1Login();
        this.team1RGB = this.gameService.getSide1Color();
        this.team2RGB = this.gameService.getSide2Color();

        if (this.gameMode == GameMode.COMPUTER) {
            Difficulty difficulty = this.gameService.getDifficulty();
            this.player2Login = "computer (" + difficulty.name() + ")";
            this.strategy = difficulty.toStrategy();
        } else if (this.gameMode == GameMode.TWOPLAYERS) {
            this.player2Login = this.gameService.getSide2Login();
        }

        this.team1ListView.getItems().add(this.player1Login);
        this.team2ListView.getItems().add(this.player2Login);
    }

    private void setButtonsGridPane(int rows, int cols) {
        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setGridLinesVisible(false);
        gridpane.setHgap(5);
        gridpane.setVgap(5);

        buttonsList = new LinkedList<Button>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Button button = new Button();
                button.setId(Integer.toString(r * rows + c));
                button.setPrefHeight(225 / rows);
                button.setPrefWidth(225 / cols);
                button.setOnAction(this::cell);
                this.buttonsList.add(button);
                gridpane.add(button, c, r);
            }
        }

        this.fieldVBox.getChildren().add(gridpane);
    }

    private void setupCube(int buttonRows, int buttonCols) {
        this.cube = new Group();
        double cubeSize = 200;
        Color color = Color.AQUA;

        List<GridPane> cubeSides = new LinkedList<GridPane>();
        for (int i = 0; i < 6; i++) {
            GridPane side = new GridPane();
            side.setVgap(0);
            side.setHgap(0);
            side.setPrefWidth(cubeSize);
            side.setPrefHeight(cubeSize);
            cubeSides.add(side);
        }

        //back
        cubeSides.get(0).setTranslateX(-0.5 * cubeSize);
        cubeSides.get(0).setTranslateY(-0.5 * cubeSize);
        cubeSides.get(0).setTranslateZ(0.5 * cubeSize);

        //bottom
        cubeSides.get(1).setTranslateX(-0.5 * cubeSize);
        cubeSides.get(1).setTranslateY(0);
        cubeSides.get(1).setRotationAxis(Rotate.X_AXIS);
        cubeSides.get(1).setRotate(90);

        //right
        cubeSides.get(2).setTranslateX(-1 * cubeSize);
        cubeSides.get(2).setTranslateY(-0.5 * cubeSize);
        cubeSides.get(2).setRotationAxis(Rotate.Y_AXIS);
        cubeSides.get(2).setRotate(90);

        //left
        cubeSides.get(3).setTranslateX(0);
        cubeSides.get(3).setTranslateY(-0.5 * cubeSize);
        cubeSides.get(3).setRotationAxis(Rotate.Y_AXIS);
        cubeSides.get(3).setRotate(90);

        //top
        cubeSides.get(4).setTranslateX(-0.5 * cubeSize);
        cubeSides.get(4).setTranslateY(-1 * cubeSize);
        cubeSides.get(4).setRotationAxis(Rotate.X_AXIS);
        cubeSides.get(4).setRotate(90);

        //front
        cubeSides.get(5).setTranslateX(-0.5 * cubeSize);
        cubeSides.get(5).setTranslateY(-0.5 * cubeSize);
        cubeSides.get(5).setTranslateZ(0.5 * cubeSize);

        this.buttonsList = new LinkedList<Button>();

        for (int i = 0; i < cubeSides.size(); i++) {
            for (int r = 0; r < buttonRows; r++) {
                for (int c = 0; c < buttonCols; c++) {
                    Button button = new Button();
                    button.setId(Integer.toString(i * buttonRows * buttonCols + r * buttonRows + c));
                    button.setPrefHeight(cubeSize / buttonRows);
                    button.setPrefWidth(cubeSize / buttonCols);
                    button.setOnAction(this::cell);
                    this.buttonsList.add(button);
                    cubeSides.get(i).add(button, c, r);
                }
            }
        }

        for (GridPane side : cubeSides) {
            this.cube.getChildren().add(side);
        }

        this.cube.getTransforms().addAll(new Rotate(-30, Rotate.X_AXIS), new Rotate(30, Rotate.Y_AXIS));

        this.fieldVBox.getChildren().add(this.cube);
        cubeSides.get(1).setVisible(false);
        cubeSides.get(3).setVisible(false);
    }

    private void showEndGameAlert() {
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
}

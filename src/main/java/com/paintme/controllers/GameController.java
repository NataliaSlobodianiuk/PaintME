package com.paintme.controllers;

import com.paintme.PaintMEException;
import com.paintme.controllers.Helpers.Alerts;
import com.paintme.domain.models.Board;
import com.paintme.infrastucture.BoardType;
import com.paintme.infrastucture.Difficulty;
import com.paintme.infrastucture.Field;
import com.paintme.infrastucture.GameMode;
import com.paintme.infrastucture.board_examiners.BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square3BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square5BoardExaminer;
import com.paintme.infrastucture.board_examiners.Square9BoardExaminer;
import com.paintme.infrastucture.strategies.GameDifficultyStrategy;
import com.paintme.services.GameService;
import com.paintme.services.UserService;
import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import com.paintme.view.graphics.Cube;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    private List<Button> buttonsList;
    private Cube cube;
    //endregion

    //region FXML Fields
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
                        this.exitButton(null);
                        return;
                    }

                    if (this.gameMode == GameMode.COMPUTER) {
                        cellNumber = this.strategy.getCellToMark(this.team2Color, "SQUARE", cells);
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
                    this.exitButton(new ActionEvent());
                    return;
                }
            } else {
                Alerts.showNotYourTurnAlert();
                return;
            }
        } catch (Exception e) {
            Alerts.showGamePropertiesAlert(e.getMessage());
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

    private void initializeBoard() throws PaintMEException {
        BoardType boardType = this.gameService.getBoardType();
        Field field = this.gameService.getBoardField();

        this.board = new Board();

        switch (boardType) {
            case _2D:
                switch (field) {
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
                switch (field) {
                    case THREE_BY_THREE:
                        this.setupCube(3, 3);
                        board.setCells(new String(new char[6 * 9]).replace("\0", "-"));
                        this.examiner = new Square3BoardExaminer();
                        break;
                    case FIVE_BY_FIVE:
                        this.setupCube(5, 5);
                        board.setCells(new String(new char[6 * 25]).replace("\0", "-"));
                        this.examiner = new Square5BoardExaminer();
                        break;
                    case NINE_BY_NINE:
                        this.setupCube(9, 9);
                        board.setCells(new String(new char[6 * 81]).replace("\0", "-"));
                        this.examiner = new Square9BoardExaminer();
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
                buttonsList.add(button);
                gridpane.add(button, c, r);
            }
        }

        this.fieldVBox.getChildren().add(gridpane);
    }

    private void setupCube(int buttonRows, int buttonCols) {
        cube = new Cube();
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-10);
        Group sceneRoot = new Group();
        Scene gameScene = this.stageManager.primaryStage.getScene();
        gameScene.setCamera(camera);

        PhongMaterial mat = new PhongMaterial();
        mat.setDiffuseMap(new Image(getClass().getResourceAsStream("/icons/DiffuseMapImage.png")));

        Group meshGroup = new Group();

        AtomicInteger cont = new AtomicInteger();
        cube.patternFaceF.forEach(p -> {
            MeshView meshP = new MeshView();
            meshP.setMesh(cube.createCube(p));
            meshP.setMaterial(mat);
            Point3D pt = cube.pointsFaceF.get(cont.getAndIncrement());
            meshP.getTransforms().addAll(new Translate(pt.getX(), pt.getY(), pt.getZ()));
            meshGroup.getChildren().add(meshP);
        });

        Rotate rotateX = new Rotate(30, 0, 0, 0, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(20, 0, 0, 0, Rotate.Y_AXIS);
        meshGroup.getTransforms().addAll(rotateX, rotateY);

        sceneRoot.getChildren().addAll(meshGroup, new AmbientLight(Color.WHITE));
        this.fieldVBox.getChildren().add(sceneRoot);

        gameScene.setOnMousePressed(me -> {
            cube.mouseOldX = me.getSceneX();
            cube.mouseOldY = me.getSceneY();
        });
        gameScene.setOnMouseDragged(me -> {
            cube.mousePosX = me.getSceneX();
            cube.mousePosY = me.getSceneY();
            rotateX.setAngle(rotateX.getAngle()-(cube.mousePosY - cube.mouseOldY));
            rotateY.setAngle(rotateY.getAngle()+(cube.mousePosX - cube.mouseOldX));
            cube.mouseOldX = cube.mousePosX;
            cube.mouseOldY = cube.mousePosY;
        });
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

package com.paintme.controllers.Helpers;

import javafx.scene.control.Alert;

public class Alerts {
    public static void showNotAFreeCellAlert(){
        Alerts.showInformationAlert("Alert", "Not A Free Cell", "This cell is already painted, choose the other one!");
    }

    public static void showNotYourTurnAlert(){
        Alerts.showInformationAlert("Alert", "Not Your Turn", "It is not your turn. Wait for the opponent!");
    }

    public static void showEndGameDrawAlert() {
        Alerts.showInformationAlert("Game Finished", "Draw", "It's a draw!");
    }

    public static void showEndGameWinnerAlert(String winner) {
        Alerts.showInformationAlert("Game Finished", "Victory", "Congrats, " + winner + "! You've won.");
    }

    public static void showEndGameLoserAlert(String loser) {
        Alerts.showInformationAlert("Game Finished", "Defeat", "Too bad, " + loser + ". You've lost. Try again!");
    }

    public static void showWrongInputAlert(String content) {
        Alerts.showInformationAlert("Alert", "Wrong Input", content);
    }

    public static void showGamePropertiesAlert(String exception) {
        Alerts.showErrorAlert("Game Properties", "Work with game properties caused an error.", exception);
    }

    public static void showUnableToGetSessionUserAlert(String exception) {
        Alerts.showErrorAlert("Game Properties", "Unable to get session user.", exception);
    }

    private static void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private static void showInformationAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

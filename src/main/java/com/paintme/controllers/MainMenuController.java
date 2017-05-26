package com.paintme.controllers;

import com.paintme.PaintMEApplication;
import com.paintme.domain.models.User;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.security.Hashing;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController{

    @Autowired
    UserRepository userRepository;

    public void playComputerModeButton(ActionEvent actionEvent) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/gameDetails.fxml"));
        fxmlLoader.setControllerFactory(PaintMEApplication.springContext::getBean);

        Parent root = fxmlLoader.load();

        Stage gameDetailsStage = new Stage();
        gameDetailsStage.setTitle("Game Details");
        gameDetailsStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
        gameDetailsStage.setScene(new Scene(root, 450, 350));
        gameDetailsStage.setResizable(false);
        gameDetailsStage.show();
    }

    public void play2PlayersModeButton(ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/gameDetails.fxml"));
        fxmlLoader.setControllerFactory(PaintMEApplication.springContext::getBean);

        Parent root = fxmlLoader.load();

        Stage gameDetailsStage = new Stage();
        gameDetailsStage.setTitle("Game Details");
        gameDetailsStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
        gameDetailsStage.setScene(new Scene(root, 450, 350));
        gameDetailsStage.setResizable(false);
        gameDetailsStage.show();
    }

    public void playOnlineModeButton(ActionEvent actionEvent) throws Exception{
        User usertoAdd = new User();
        usertoAdd.setLogin("julia");
        usertoAdd.setPasswordSalt(Hashing.getSalt("SHA1PRNG"));
        usertoAdd.setPasswordHash(Hashing.getSecurePassword("mypassword", usertoAdd.getPasswordSalt(), "SHA-256"));
        usertoAdd.setEmail("julia@example.com");
        this.userRepository.save(usertoAdd);
        this.userRepository.findByLogin("login");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/fxml/signIn.fxml"));
        fxmlLoader.setControllerFactory(PaintMEApplication.springContext::getBean);

        Parent root = fxmlLoader.load();

        Stage signInStage = new Stage();
        signInStage.setTitle("Sign In");
        signInStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
        signInStage.setScene(new Scene(root, 350, 300));
        signInStage.setResizable(false);
        signInStage.show();
    }
}

package com.paintme.view;

import com.paintme.spring.config.SpringFXMLLoader;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class StageManager {
    private static final Logger log = getLogger(StageManager.class);
    private final Stage primaryStage;
    private final SpringFXMLLoader springFXMLLoader;

    public StageManager(Stage primaryStage, SpringFXMLLoader springFXMLLoader){
        this.primaryStage = primaryStage;
        this.springFXMLLoader = springFXMLLoader;
    }

    public void switchScene(final FxmlView fxmlView){
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(fxmlView.getFxmlFile());
        show(viewRootNodeHierarchy, fxmlView);
    }

    private void show(final Parent rootNode, FxmlView fxmlView){
        rootNode.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");

        Scene scene = prepareScene(rootNode, fxmlView);

        this.primaryStage.setTitle(fxmlView.setTitle());
        this.primaryStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
        this.primaryStage.setScene(scene);
        this.primaryStage.sizeToScene();
        this.primaryStage.centerOnScreen();
        primaryStage.setResizable(false);

        try{
            primaryStage.show();
        }
        catch(Exception exception){
            logAndExit("Unable to show scene for title " + fxmlView.setTitle(), exception);
        }
    }

    private Scene prepareScene(Parent rootNode, FxmlView fxmlView){
        Scene scene = new Scene(rootNode, fxmlView.setWidth(), fxmlView.setHeight());
        scene.setRoot(rootNode);

        return scene;
    }

    private Parent loadViewNodeHierarchy(String fxmlPath){
        Parent rootNode = null;

        try{
            rootNode = springFXMLLoader.load(fxmlPath);
            Objects.requireNonNull(rootNode, "An FXML root node must not be null");
        }
        catch(Exception exception){
            logAndExit("Unable to load an FXML view " + fxmlPath, exception);
        }

        return rootNode;
    }

    private void logAndExit(String errorMessage, Exception exception){
        log.error(errorMessage, exception, exception.getCause());
        Platform.exit();
    }
}

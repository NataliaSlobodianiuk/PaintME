package com.paintme.spring.config;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class SpringFXMLLoader {
    private final ApplicationContext context;

    @Autowired
    public SpringFXMLLoader(ApplicationContext context){
        this.context = context;
    }

    public Parent load(String fxmlPath) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(this.context::getBean);
        fxmlLoader.setLocation(getClass().getResource(fxmlPath));

        return fxmlLoader.load();
    }
}

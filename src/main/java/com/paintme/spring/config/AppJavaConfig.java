package com.paintme.spring.config;

import  com.paintme.logging.ExceptionWriter;
import com.paintme.view.StageManager;
import java.io.IOException;
import java.io.StringWriter;

import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
public class AppJavaConfig {
    @Autowired
    private SpringFXMLLoader springFXMLLoader;

    @Bean
    @Scope("prototype")
    public ExceptionWriter exceptionWriter() {
        return new ExceptionWriter(new StringWriter());
    }

    @Bean
    @Lazy(value = true)
    public StageManager stageManager(Stage stage) throws IOException {
        return new StageManager(stage, springFXMLLoader);
    }
}

package com.paintme;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PaintMEApplication extends Application {

	private ConfigurableApplicationContext springContext;

	@FXML
	private GridPane root;

	public static void main(String[] args) {
		Application.launch(args);
	};

	@Override
	public void init() throws Exception {
		this.springContext = SpringApplication.run(PaintMEApplication.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainMenu.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		this.root = fxmlLoader.load();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("PaintME");
		primaryStage.getIcons().add(new Image("/icons/5x5Cube.jpg"));
		root.setStyle("-fx-background-image:url('/icons/BackgroundImage.jpg')");
		primaryStage.setScene(new Scene(this.root, 350, 300));

		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		this.springContext.close();
	}
}
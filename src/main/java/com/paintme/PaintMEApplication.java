package com.paintme;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PaintMEApplication extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent root;

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
		primaryStage.setScene(new Scene(this.root, 300, 275));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		this.springContext.close();
	}
}
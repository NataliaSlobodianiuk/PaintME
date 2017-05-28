package com.paintme;

import com.paintme.view.FxmlView;
import com.paintme.view.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PaintMEApplication extends Application {

	protected ConfigurableApplicationContext springContext;
	protected StageManager stageManager;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		this.springContext = bootstrapSpringApplicationContext();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stageManager = this.springContext.getBean(StageManager.class, primaryStage);
		displayInitialScene();
	}

	@Override
	public void stop() throws Exception {
		this.springContext.close();
	}

	protected void displayInitialScene(){
		this.stageManager.switchScene(FxmlView.MAIN);
	}

	private ConfigurableApplicationContext bootstrapSpringApplicationContext() {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PaintMEApplication.class);
		String[] args = getParameters().getRaw().stream().toArray(String[]::new);
		builder.headless(false);
		return builder.run(args);
	}
}
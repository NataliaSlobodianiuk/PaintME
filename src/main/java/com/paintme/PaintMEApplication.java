package com.paintme;

import com.paintme.domain.models.User;
import com.paintme.domain.models.statuses.UserStatuses;
import com.paintme.domain.repositories.UserRepository;
import com.paintme.services.UserService;
import com.paintme.services.UserServiceImpl;
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

    protected UserService userService;
	protected UserRepository userRepository;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		this.springContext = bootstrapSpringApplicationContext();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.stageManager = this.springContext.getBean(StageManager.class, primaryStage);
        this.userService = this.springContext.getBean(UserServiceImpl.class);
		this.userRepository = this.springContext.getBean(UserRepository.class);
		displayInitialScene();
	}

	@Override
	public void stop() throws Exception {
        User user = this.userService.getSessionUser();

        if (user != null) {
            user.setStatus(UserStatuses.OFFLINE);
            this.userRepository.save(user);
        }

		springContext.close();
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
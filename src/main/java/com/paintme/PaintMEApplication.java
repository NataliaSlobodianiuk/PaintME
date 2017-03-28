package com.paintme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.paintme.models.*;

@SpringBootApplication
public class PaintMEApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaintMEApplication.class, args);

		User user = new User();
	}
}

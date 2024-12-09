package com.example.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TodoApplication.class);
		app.run(args);
	}
}

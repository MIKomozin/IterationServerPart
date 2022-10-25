package com.example.IterationProject;

import com.example.IterationProject.Controllers.MainController;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IterationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IterationProjectApplication.class, args);
	}

	@Bean
	ApplicationRunner init(MainController mainController) {
		//При запуске приложения происходит считывание актуальных курсов валют с сайта ЦБ
		return args -> {mainController.addValutesInDataBase();
		};
	}
}
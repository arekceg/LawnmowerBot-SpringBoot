package com.arek.lawnmowerbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LawnmowerbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(LawnmowerbotApplication.class, args);
	}

}

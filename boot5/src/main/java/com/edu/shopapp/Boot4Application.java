package com.edu.shopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Boot4Application {

	public static void main(String[] args) {
		SpringApplication.run(Boot4Application.class, args);
	}

}

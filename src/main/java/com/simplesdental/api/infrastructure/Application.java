package com.simplesdental.api.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.simplesdental.api.domain")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

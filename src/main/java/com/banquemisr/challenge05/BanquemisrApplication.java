package com.example.banquemisr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BanquemisrApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanquemisrApplication.class, args);
	}

}

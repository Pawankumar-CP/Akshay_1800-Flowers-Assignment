package com.cpa.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(scanBasePackages = { "com.cpa.web.rest", "com.cpa.entity", "com.cpa.service",
		"com.cpa.service.impl", "com.cpa.kafka.impl" })
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
		System.out.println("method called in main class");
	}

	@Bean
	public WebClient.Builder getWebClient() {
		return WebClient.builder();
	}

}

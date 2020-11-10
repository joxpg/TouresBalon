package com.puj.aes.traductor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TraductorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraductorApplication.class, args);
	}

}

package com.puj.aes.pasarela.pasarela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication

public class PasarelaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasarelaApplication.class, args);
	}

}

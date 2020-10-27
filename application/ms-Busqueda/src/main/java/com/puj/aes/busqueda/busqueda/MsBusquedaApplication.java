package com.puj.aes.busqueda.busqueda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsBusquedaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBusquedaApplication.class, args);
	}

}

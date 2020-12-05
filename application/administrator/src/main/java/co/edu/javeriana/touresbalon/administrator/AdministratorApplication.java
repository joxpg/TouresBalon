package co.edu.javeriana.touresbalon.administrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;


import de.codecentric.boot.admin.server.config.*;


@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@SpringBootApplication
@EnableEurekaClient
public class AdministratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministratorApplication.class, args);
	}

}

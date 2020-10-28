package co.com.puj.aes.msPasarela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsPasarelaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPasarelaApplication.class, args);
	}

}

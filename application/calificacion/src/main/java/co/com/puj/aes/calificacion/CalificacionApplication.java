package co.com.puj.aes.calificacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CalificacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalificacionApplication.class, args);
    }

}

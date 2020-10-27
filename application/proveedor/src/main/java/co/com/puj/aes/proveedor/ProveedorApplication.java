package co.com.puj.aes.proveedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProveedorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProveedorApplication.class, args);
    }

}

# ¿Qué es Eureka?

Eureka es un servicio REST, utilizándose principalmente en la nube de AWS, a la cuál está estrechamente ligado. Eureka se comporta como servidor, cuyo objetivo es registrar y localizar microservicios existentes, informar de su localización, su estado y datos relevantes de cada uno de ellos. Además, nos facilita el balanceo de carga y tolerancia a fallos.

# Comandos para ejecutar el Eureka Server

Se debe ejecutar 
``` 
mvn install
```
Para correrlo localmente 
``` 
mvn spring-boot:run
```
# Datos importantes

Este Eureka se desplegará automaticamente por el puerto 9040.

# Registrar un cliente 

En la raiz del microservicio que queremos registrar al eureka, se debe añadir la dependencia spring-cloud-starter-eureka en el archivo pom.xml

```<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
      <version>2.2.5.RELEASE</version>
	</dependency>
```

Añadir la anotación @EnableEurekaClient en la clase principal de la aplicación SpringBoot. Ejemplo:

```
package co.com.puj.aes.proveedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
@SpringBootApplication
@EnableEurekaClient

public class ProveedorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProveedorApplication.class, args);
    }

}

```

Adicional si estamos manejando application.properties para el manejo de las configuraciones incluir la siguiente configuración al final del archivo:

```
#Configuraciones Eureka
eureka.client.serviceUrl.defaultZone  = http://localhost:9040/eureka/
eureka.client.instance.preferIpAddress = true
spring.application.name = eurekaclient
```
NOTA: Reemplazar el valor eurekaclient por el nombre de su microservicio.
En caso que manejen las configuraciones por application.yml, se debe incluir las siguientes lineas:
``
eureka:
   client:
      serviceUrl:
         defaultZone: http://localhost:8761/eureka/
      instance:
      preferIpAddress: true
spring:
   application:
      name: eurekaclient
```
Poner a correr el microservicio y revisar eureka en el navegador en la siguiente direccion http://localhost:9040/. Deberiamos ver la aplicación registrada.

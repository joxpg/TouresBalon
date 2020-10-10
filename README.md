# TouresBalon


## Descripción

Este repositorio tiene como fin documentar la arquitectura de Toures Balón para  la materia de Proyecto de Implementación de la Arquitectura PUJ, en el presente documento encontrarála definición de la arquitectura propuesta por el equipo J4K de la Especialización Arquitectura Empresarial de Software.

Integrantes:

- Kattya Alexandra Peña Nieto 
- Johann Fernando Trigos Niño 
- Javier Alfonso Becerra Sanchez 
- José Esteban Betin Diaz 
- Johan Miguel Céspedes Ortega


## Tabla de contenido <a name="table-of-contents"></a>

1. [Estilo de arquitectura](#estilo-arquitectura).
2. [Patrones y herramientas](#patrones-herramientas).
3. [Arquitectura](#arquitectrua)
    1. [Vista de proceso](#vista-proceso)
    2. [Vista estructural](#vista-estructural)
4. [Justificación de arquitectura](#justificacion-arquitectura).
    1. [Descisiones](#decisiones-arquitectura)
    2. [TradeOff](#tradeoff).
5. [Artefactos](#artefactos).
    1. [Reservas](#reservas)
    2. [Pagos](#pagos)
    3. [Calificaciones](#calificaciones)
    4. [Ordenes](#ordenes)
    5. [Pasarela](#pasarela)
    6. [Trasporte](#trasporte)
    7. [Hospedaje](#hospedaje)
    7. [Proveedores](#proveedores)
    7. [Espectáculo](#espectaculo)
    7. [Búsqueda](#busqueda)
6. [Referencias](#referencias).

## 1. Estilo de arquitectura <a name="estilo-arquitectura"></a>
La arquitectura orientada a microservicios (SOA) es un estilo arquitectónico que nos permite definir un conjunto de servicios de colaboración debilmente acoplados, esto favorece la rápida implementación de nuevos componentes, de manera rapida y sencilla, es posible distribuir resoponsabilidades entre servicios de aplicación lo cual reduce el acoplamiento y aumenta la cohesión, siendo esto un factor importante de la solución, los microservicios permitiran llevar a cabo una facil integración entre componentes independiente de la tecnologia que implementen, asi mismo puede llevarse a cabo desarrollo de manera incremental, rapida y continua de cada funcionalidad, los costes de mantenimiento y su impacto para el negocio serán menores, aunque su implementación demande mayor inversión al inicio, a futuro se podrá ver un amplio ahora respecto del mantenimiento y escalamiento<br/>
Es importante resaltar que la arquitectura propuesta por el equipo J4K presenta una solucion optima a los problemas tecnologicos y perdida de utilidades que viene presentado Toures Balón durante los últimos eventos, por tanto el estilo presetado a continuación evolucionara de manera natural, su gestión será más sencilla, utilizando los recursos disponibles con una minima invesión bajo el empleo de algunas herramientas descritas en adelante<br/>

## 2. Patrones y herramientas <a name="patrones-herramientas"></a>

### Patrones
PATRÓN NUCLEAR:

MICROSERVICIOS: Como se definió anteriormente, este patrón permitirá que todo el desarrollo se rija frente a la formación del proceso a través de la composición de los servicios propuestos de este comunicandose de diferentes maneras, dandole valor al negocio.

|    Patrón   |                                                                                                                                                       descripción                                                                                                                                                      |                           Representación                          |
|:-----------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:-----------------------------------------------------------------:|
| Api-Gateway | Dado a la definición del primer patrón, es necesario una puerta de enlace hacia las interfaces de consumo, para acceder a este servicio. Para su aplicación se puede hacer uso de herramientas que cuentan frameworks tales como Spring Cloud Gateway, AWS Api Gateway, entre otros.                                   | <img src="https://microservices.io/i/apigateway.jpg" width="600"> |
|    Broker   | Es un patrón de arquitectura que se utiliza en sistemas de software distribuidos con servicios desacoplados que interactúan por invocaciones de servicios remotos. Será el responsable de coordinar la comunicación, tanto de enviar/reenviar las peticiones, así como de transmitir los resultados y las excepciones. |                                                                   |
|   Pub-Sub   |                                                                                                                                                                                                                                                                                                                        |                                                                   |


### Herramientas
Para la implementación de Arquitectura se implementarán las siguientes herramientas.

| Herramienta  | Descripción                                                                                                                                                                           |
|--------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| OpenAPI         | Para el desarrollo de los contratos, este ofrece una herramienta de código abierto para su diseño.                                                                                    |
| Spring Boot  | Spring Framework proporciona soporte fundamental para diferentes arquitecturas de aplicaciones, incluida la mensajería, los datos transaccionales y la persistencia, y la web. También incluye el marco web Spring MVC basado en Servlet y, en paralelo, el marco web reactivo Spring WebFlux.                   |
| Kafka        | Debido a que su orientación mediante patrón de Publicador Suscriptor es una gran herramienta para actuar como coreógrafo del proceso.                                                 |
| Zuul         | Con el fin de modelar el api-gateway, este ofrece grandes herramientas para ser la puerta de enlace para las interfaces.                                                              |
| Spring Cloud | Debido a que se debe interactuar entre varios componentes este framework ofrece funcionalidades para ambientes distribuidos como clientes de consumo sencillos hacia otros servicios. |
| Eureka       | Para conocer los servicios de los proveedores, este actúa como el encargado de registrar los microservicios / servicios que compone el proceso.                                       |


## 3. Arquitectura <a name="arquitectrua"></a>
## 3.1 Vista de proceso <a name="vista-proceso"></a>
A continuación, se presenta en la figura 1 la vista de proceso ilustrada por un diagrama de archi en la cual se presenta el flujo del proceso para proveedores de toures Balón.

## 3.2 Vista estructural <a name="vista-estructural"></a>

La vista estructural se presenta por medio del diagrama de componentes. En este diagrama podemos describir algunos de los principales patrones y decisiones arquitectónicas que se tomaron, decisiones que explicaremos en la siguiente sección.

![alt text][fig2]

Figura 2: Diagrama de componentes

La arquitectura candidata pretende aportar a Toures Balón independencia en la gestión, reducir el acoplamiento en las operaciones que realiza su sistema, y tolerancia a fallos entre otros, por ello presentamos proveedores que se comunican a través de la capa anticorrupción por un adaptador cuya tarea con las interfaces de proveedor, será la de traducir los datos que provienen de terceros reduciendo la posibilidad de fallos por el empleo de diferentes tecnologias, de esta forma ser enviados por intermedio de los servicios hacia el sistema de colas permanentes, esta lleva a cabo la composición de servicios para registrar proveedores, consultar reservas, realizar búsquedas y completar transacciones de pago y demás acciones requeridas por el negocio.<br/>

Estas últimas operaciones seran procesadas de cara al proveedor con ayuda de la capa Aticorrupcion Outbound quien con la implementación de adaptadores de servicios registran los pagos en los proveedores. Es importante aclarar que se ha implementado un registro de servicios donde puedan persistir los end points de proveedores con los cuales el sistema se comunicará, asi mismo se cuenta con un api gateway como enlace hacia el interior de Toures Balón. 

## 4. Justificación de arquitectura <a name="justificacion-arquitectura"></a>

## 4.1 Decisiones de arquitectura <a name="decisiones-arquitectura"></a>
## 4.2. TradeOff de la Arquitectura <a name="tradeoff"></a>

## 5. Artefactos <a name="artefactos"></a>
1. [Reserva][contrato-reserva]
2. [Notificación][contrato-notificacion]
3. [Transporte][contrato-transporte]

## 6. Referencias <a name="referencias"></a>

[contrato-reserva]: contratos/reserva.json
[contrato-notificacion]: contratos/NotificationV2.yaml
[contrato-transporte]: contratos/transporte.yaml
[fig1]: /img/diagramaNegocio.png "Módelo de procesos"
[fig2]: /img/diagramaComponentes.jpg "Diagrama de componentes"


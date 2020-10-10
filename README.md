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

|    Patrón   | descripción | Representación |
|:-----------:|:-----------:|:--------------:|
| Api-Gateway |             |                |
|    Broker   |             |                |
|   Pub-Sub   |             |                |








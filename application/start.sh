cd eureka-server
mvn install
docker rm -f eureka-server
docker build -t eureka-server:lastest .
docker run -p 9040:9040 -d --name eureka-server eureka-server:lastest
cd ..
cd zuul
mvn install
docker rm -f zuul
docker build -t zuul:lastest .
docker run -p 9041:9041 -d --name zuul zuul:lastest
cd ..
cd calificacion
mvn install
docker rm -f calificacion
docker build -t calificacion:lastest .
docker run -p 9060:9060 -d --name calificacion calificacion:lastest
cd ..
cd reserva
mvn install
docker rm -f reserva
docker build -t reserva:lastest .
docker run -p 9061:9061 -d --name reserva reserva:lastest
cd ..
cd proveedor
mvn install
docker rm -f proveedor
docker build -t proveedor:lastest .
docker run -p 9062:9062 -d --name proveedor proveedor:lastest
cd ..
cd ms-Busqueda
mvn install
docker rm -f ms-Busqueda
docker build -t ms-Busqueda:lastest .
docker run -p 9070:9070 -d --name ms-Busqueda ms-Busqueda:lastest
cd ..
cd ms-Pagos
mvn install
docker rm -f ms-Pagos
docker build -t ms-Pagos:lastest .
docker run -p 9071:9071 -d --name ms-Pagos ms-Pagos:lastest
cd ..
cd ms-pasarela
mvn install
docker rm -f ms-pasarela
docker build -t ms-pasarela:lastest .
docker run -p 9072:9072 -d --name ms-pasarela ms-pasarela:lastest
cd ..
cd Producto
mvn install
docker rm -f Producto
docker build -t Producto:lastest .
docker run -p 9080:9080 -d --name Producto Producto:lastest
cd ..
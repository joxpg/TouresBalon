docker-compose down -v
cd eureka-server
mvn install
docker rm -f eureka-server
docker build -t eureka-server:lastest .
cd ..
cd zuul
mvn install
docker rm -f zuul
docker build -t zuul:lastest .
cd ..
cd calificacion
mvn install
docker rm -f calificacion
docker build -t calificacion:lastest .
cd ..
cd reserva
mvn install
docker rm -f reserva
docker build -t reserva:lastest .
cd ..
cd proveedor
mvn install
docker rm -f proveedor
docker build -t proveedor:lastest .
cd ..
cd ms-Busqueda
mvn install
docker rm -f ms-busqueda
docker build -t ms-busqueda:lastest .
cd ..
cd ms-Pagos
mvn install
docker rm -f ms-pagos
docker build -t ms-pagos:lastest .
cd ..
cd ms-Pasarela
mvn install
docker rm -f ms-pasarela
docker build -t ms-pasarela:lastest .
cd ..
cd Producto
mvn install
docker rm -f producto
docker build -t producto:lastest .
cd ..
cd AnticorruptionLayer
cd SoapAdapter
dotnet build -c Release
docker build -t soapadapter:lastest .
cd ..
cd ..
cd AnticorruptionLayer
cd RestAdapter
dotnet build -c Release
docker build -t restadapter:lastest .
cd ..
cd ..
cd AnticorruptionLayer
cd RouterProviderService
dotnet build -c Release
docker build -t routerproviderservice:lastest .
cd ..
cd ..


docker-compose up -d

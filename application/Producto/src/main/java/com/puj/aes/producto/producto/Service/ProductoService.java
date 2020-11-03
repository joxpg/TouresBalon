package com.puj.aes.producto.producto.Service;


import com.google.gson.Gson;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.puj.aes.producto.producto.Controller.ProductoController;
import com.puj.aes.producto.producto.Entity.ProductoBusqueda;
import com.puj.aes.producto.producto.Entity.ProductoResultado;
import com.puj.aes.producto.producto.Entity.Proveedor;
import com.puj.aes.producto.producto.Interface.IProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.cloud.client.ServiceInstance;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductoService implements IProductoService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Qualifier("getWebClientBuilder")
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    ProductoController productoController;
    public ProductoService(ProductoController productoController) {this.productoController = productoController;}

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void enviarRespuesta(Proveedor proveedor, ProductoResultado respuesta) {
        //TODO Obtener la calificación y peso convenio del proveedor enviado
        String calificacion = proveedor.getRating();
        String pesoConvenio = proveedor.getWeight();
        respuesta.setCalificacion(calificacion);
        respuesta.setPesoConvenio(pesoConvenio);
        productoController.producerRespuesta(respuesta);
    }

    @Override
    public void buscarProducto(String busqueda) {
        Gson g = new Gson();
        ProductoBusqueda productoBusqueda = g.fromJson(busqueda, ProductoBusqueda.class);
        String productType = productoBusqueda.getProductType();
        List<Proveedor> proveedorList = this.obtenerProveedores(productType);
        for (Proveedor provedor: proveedorList)
        {
            ProductoBusqueda productoBusquedaAux = productoBusqueda;
            this.enviarPeticion(provedor,productoBusquedaAux);
        }
    }

    private List<Proveedor> obtenerProveedores(String tipoProducto){
        //List<Proveedor> proveedorList = restTemplate.getForObject("https://run.mocky.io/v3/f8ece200-5a9d-4855-92d3-3fe20d5c497a", List.class);
        //tipoProducto.toUpperCase();
        //List<ServiceInstance> urls = discoveryClient.getInstances("ms-proveedor");
        Proveedor[] proveedores = restTemplate.getForObject(
                "http://ms-proveedor/proveedor/product/"+tipoProducto.toUpperCase(),
                Proveedor[].class);
        List<Proveedor> proveedorList = Arrays.stream(proveedores).collect(Collectors.toList());
        return proveedorList;
    }

    private void enviarPeticion(Proveedor provider, ProductoBusqueda busqueda){
        //TODO enviar a servicio que mapea los campos
        //TODO enviar la petición al servicio de proveedores
        List<ProductoResultado> resultadoList = Arrays.stream(restTemplate.getForObject(
                "https://run.mocky.io/v3/6bbaf064-e11c-49de-a81d-5eb743b9e2bb",
                ProductoResultado[].class)).collect(Collectors.toList());
        for (ProductoResultado pr: resultadoList)
        {
            this.enviarRespuesta(provider, pr);
        }
    }

    //TODO Servicio que gestione la búsqueda por proveedor
    //Se debe consultar el servicio de proveedor para obetner los proveedores que ofrecen el tipo producto consultado
    //Se debe crear una consulta a los servicios externos por cada proveedor resultante de la búsqueda
    //Por cada elemento de la respuesta enviada por el servicio de cada proveedor se debe enviar un mensaje a la cola con la respuesta

}

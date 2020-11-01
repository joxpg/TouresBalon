package com.puj.aes.producto.producto.Service;


import com.puj.aes.producto.producto.Entity.*;
import java.util.*;
import com.google.gson.*;
import com.puj.aes.producto.producto.Interface.IProductoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductoService implements IProductoService {
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public ProductoResultado enviarRespuesta(ProductoResultado respuesta) {
        float calificacion = 4;
        float pesoConvenio = 2;
        //TODO Obtener la calificación y peso convenio de los servicios

        respuesta.setCalificacion(calificacion);
        respuesta.setPesoConvenio(pesoConvenio);
        return respuesta;
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
        List<Proveedor> proveedorList = restTemplate.getForObject("https://run.mocky.io/v3/f8ece200-5a9d-4855-92d3-3fe20d5c497a", List.class);
        return proveedorList;
    }

    private ProductoResultado enviarPeticion(Proveedor provider, ProductoBusqueda busqueda){
        //TODO enviar a servicio que mapea los campos
        //TODO enviar la petición al servicio de proveedores
        return null;
    }

    //TODO Servicio que gestione la búsqueda por proveedor
    //Se debe consultar el servicio de proveedor para obetner los proveedores que ofrecen el tipo producto consultado
    //Se debe crear una consulta a los servicios externos por cada proveedor resultante de la búsqueda
    //Por cada elemento de la respuesta enviada por el servicio de cada proveedor se debe enviar un mensaje a la cola con la respuesta

}

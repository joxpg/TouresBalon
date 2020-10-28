package com.puj.aes.producto.producto.Service;


import com.puj.aes.producto.producto.Entity.*;
import java.util.*;

import com.puj.aes.producto.producto.Interface.IProductoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductoService implements IProductoService {
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
    public ProductoResultado buscarProducto(String busqueda) {
        //TODO mapear busqueda en ProductoBusqueda
        return null;
    }

    private List<Proveedor> obtenerProveedores(String tipoProducto){
        return null;
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

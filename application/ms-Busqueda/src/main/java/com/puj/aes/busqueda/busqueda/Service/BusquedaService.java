package com.puj.aes.busqueda.busqueda.Service;

import com.puj.aes.busqueda.busqueda.Entity.BusquedaProducto;
import com.puj.aes.busqueda.busqueda.Interface.IBusquedaService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class BusquedaService implements IBusquedaService {
    public BusquedaProducto enviarRespuesta(@Valid BusquedaProducto respuesta) {
        Double calificacion = 4.0;
        Double pesoConvenio = 2.0;

        return respuesta;
    }

}

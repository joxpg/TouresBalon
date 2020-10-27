package com.puj.aes.producto.producto.Service;

import com.puj.aes.producto.producto.Entity.Busqueda;
import com.puj.aes.producto.producto.Interface.IBusquedaService;
import org.springframework.stereotype.Service;

@Service
public class BusquedaService implements IBusquedaService {
    @Override
    public Busqueda enviarRespuesta(Busqueda respuesta) {
        Double calificacion = 4.0;
        Double pesoConvenio = 2.0;

        return respuesta;
    }
}

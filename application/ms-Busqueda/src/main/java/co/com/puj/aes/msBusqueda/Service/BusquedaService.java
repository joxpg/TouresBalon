package co.com.puj.aes.msBusqueda.Service;

import co.com.puj.aes.msBusqueda.Entity.BusquedaProducto;
import co.com.puj.aes.msBusqueda.Interface.IBusquedaService;
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

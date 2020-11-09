package co.com.puj.aes.msBusqueda.Service;

import co.com.puj.aes.msBusqueda.Entity.BusquedaProducto;
import co.com.puj.aes.msBusqueda.Interface.IBusquedaService;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import java.util.List;

@Service
public class BusquedaService implements IBusquedaService {
    public BusquedaProducto enviarRespuesta(@Valid BusquedaProducto respuesta) {
        Double calificacion = 4.0;
        Double pesoConvenio = 2.0;

        return respuesta;
    }


}

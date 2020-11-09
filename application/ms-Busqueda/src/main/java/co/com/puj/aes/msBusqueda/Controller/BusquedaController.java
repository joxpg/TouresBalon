package co.com.puj.aes.msBusqueda.Controller;

import co.com.puj.aes.msBusqueda.Entity.BusquedaProducto;
import co.com.puj.aes.msBusqueda.Entity.BusquedaReserva;
import co.com.puj.aes.msBusqueda.Service.BusquedaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/busqueda")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class BusquedaController {
    @Autowired
    private KafkaTemplate<String, BusquedaProducto> kafkaTemplateproducto;
    @Autowired
    private KafkaTemplate<String, BusquedaReserva> kafkaTemplatereserva;
    //private static final String TOPIC = "productobusqueda";
    @Autowired
    BusquedaService busquedaService;
    public BusquedaController(BusquedaService busquedaService) {this.busquedaService = busquedaService;}

    /*@PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody BusquedaProducto busquedaproducto) throws Exception {
        busquedaproducto = busquedaService.enviarRespuesta(busquedaproducto);
        kafkaTemplate.send(TOPIC, busquedaproducto);
        return new ResponseEntity<>("Escribiendo en kafka",HttpStatus.OK);
    }*/

    @PostMapping("")
    public void servicioBusqueda(@Valid @RequestBody BusquedaProducto busquedaproducto){
        kafkaTemplateproducto.send("productobusqueda", busquedaproducto);
    }

    @PostMapping(value = "reserva")
    public void servicioReserva(@Valid @RequestBody BusquedaReserva busquedaReserva) throws Exception {
        kafkaTemplatereserva.send("calificacion", busquedaReserva);
    }

    @KafkaListener(topics = "productoresultado", groupId = "productoresultado")
    public String consumerBusqueda(String busqueda){
        System.out.println(" Mensaje entrante de un producto = " + busqueda);
        return busqueda;
    }
}

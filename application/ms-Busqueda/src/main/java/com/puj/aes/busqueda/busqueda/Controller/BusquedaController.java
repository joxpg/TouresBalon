package com.puj.aes.producto.producto.Controller;

import com.puj.aes.producto.producto.Entity.Busqueda;
import com.puj.aes.producto.producto.Service.BusquedaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/busqueda")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class BusquedaController {
    @Autowired
    private KafkaTemplate<String, Busqueda> kafkaTemplate;
    private static final String TOPIC = "busqueda";
    @Autowired
    BusquedaService busquedaService;
    public BusquedaController(BusquedaService busquedaService) {this.busquedaService = busquedaService;}

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Busqueda busqueda) throws Exception {
        busqueda = busquedaService.enviarRespuesta(busqueda);
        kafkaTemplate.send(TOPIC, busqueda);
        return new ResponseEntity<>("Escribiendo en kafka",HttpStatus.OK);
    }
}

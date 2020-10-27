package com.puj.aes.busqueda.busqueda.Controller;

import com.puj.aes.busqueda.busqueda.Entity.BusquedaProducto;
import com.puj.aes.busqueda.busqueda.Service.BusquedaService;
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
    private KafkaTemplate<String, BusquedaProducto> kafkaTemplate;
    private static final String TOPIC = "productobusqueda";
    @Autowired
    BusquedaService busquedaService;
    public BusquedaController(BusquedaService busquedaService) {this.busquedaService = busquedaService;}

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody BusquedaProducto busquedaproducto) throws Exception {
        busquedaproducto = busquedaService.enviarRespuesta(busquedaproducto);
        kafkaTemplate.send(TOPIC, busquedaproducto);
        return new ResponseEntity<>("Escribiendo en kafka",HttpStatus.OK);
    }
}

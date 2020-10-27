package com.puj.aes.pasarela.pasarela.Controller;

import com.puj.aes.pasarela.pasarela.Entity.Pasarela;
import com.puj.aes.pasarela.pasarela.Service.PasarelaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/pagos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class PasarelaController {
    @Autowired
    private KafkaTemplate<String, Pasarela> kafkaTemplate;
    private static final String TOPIC = "pagos";
    @Autowired
    PasarelaService pasarelaService;
    public PasarelaController(PasarelaService pasarelaService) {this.pasarelaService = pasarelaService;}

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody Pasarela pasarela) throws Exception {
        pasarela = pasarelaService.enviarRespuesta(pasarela);
        kafkaTemplate.send(TOPIC, pasarela);
        return new ResponseEntity<>("Escribiendo en kafka",HttpStatus.OK);
    }
}

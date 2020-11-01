package com.puj.aes.producto.producto.Controller;

import com.puj.aes.producto.producto.Entity.ProductoBusqueda;
import com.puj.aes.producto.producto.Entity.ProductoResultado;
import com.puj.aes.producto.producto.Service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/producto")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class ProductoController {
    @Autowired
    private KafkaTemplate<String, ProductoResultado> kafkaTemplate;
    private static final String TOPIC = "productoResultado";
    @Autowired
    ProductoService productoService;
    public ProductoController(ProductoService productoService) {this.productoService = productoService;}

    @PostMapping("")
    public void producerRespuesesta(@Valid @RequestBody ProductoResultado producto) throws Exception {
        producto = productoService.enviarRespuesta(producto);
        kafkaTemplate.send(TOPIC, producto);
    }

    @KafkaListener(topics = "productoBusqueda", groupId = "producto")
    public void consumerBusqueda(String producto){
        productoService.buscarProducto(producto);

    }
}

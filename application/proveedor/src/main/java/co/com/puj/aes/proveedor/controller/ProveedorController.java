package co.com.puj.aes.proveedor.controller;

import co.com.puj.aes.proveedor.entity.Calificacion;
import co.com.puj.aes.proveedor.entity.Proveedor;
import co.com.puj.aes.proveedor.entity.Reserva;
import co.com.puj.aes.proveedor.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/proveedor")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService){this.proveedorService = proveedorService;}
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Qualifier("getWebClientBuilder")
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private KafkaTemplate<String, Proveedor> kafkaTemplate;
    private static final String TOPIC = "reserva";

    @GetMapping("product/{type}")
    public ResponseEntity<?> getlistByProductType(@PathVariable String type) throws Exception {
        List<Proveedor> list = proveedorService.getlistByProductType(type);
        if(!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<Proveedor> list = proveedorService.getlist();
        if(!list.isEmpty()){
            //return new ResponseEntity<List>(list, HttpStatus.OK);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Proveedor proveedor) throws Exception {
        return new ResponseEntity<>(proveedorService.save(proveedor), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") String id ) throws Exception {
        Proveedor proveedor = proveedorService.getProveedorById(id);
        if(proveedor==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(proveedorService.getProveedorById(id),HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") String idProveedor, @RequestBody Proveedor proveedor) throws Exception {
        Proveedor proveedor1 = proveedorService.getProveedorById(idProveedor);
       // Calificacion calificacion = restTemplate.getForObject("http://ms-calificacion/calificacion/"+ idProveedor+"/proveedor", Calificacion.class);
        if(proveedor1==null){
            return new ResponseEntity<>("No existe un Proveedor correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        proveedor.setIdProvider(idProveedor);
        return new ResponseEntity<>(proveedorService.update(idProveedor, proveedor),HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("{idProveedor}")
    public ResponseEntity <?> delete(@PathVariable("idProveedor") String idProveedor) throws Exception {
        Proveedor proveedor = proveedorService.getProveedorById(idProveedor);
        if(proveedor==null /*|| !proveedorService.existeById(id)*/){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(proveedorService.deleteProveedor(idProveedor), HttpStatus.OK);
    }
}

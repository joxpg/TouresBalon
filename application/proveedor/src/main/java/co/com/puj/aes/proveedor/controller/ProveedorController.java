package co.com.puj.aes.proveedor.controller;

import co.com.puj.aes.proveedor.entity.Calificacion;
import co.com.puj.aes.proveedor.entity.Proveedor;
import co.com.puj.aes.proveedor.service.ProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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
        kafkaTemplate.send(TOPIC, proveedor);
        return new ResponseEntity<>(proveedorService.getProveedorById(id),HttpStatus.OK);
    }
    @KafkaListener(topics = "reserva", groupId = "proveedor")
    public String consumerProveedor (String reserva){
        System.out.println("Mensaje entrante de nueva reserva" + reserva);
        return reserva;
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") String idProveedor, @RequestBody Proveedor proveedor) throws Exception {
        System.out.println("Calificacion = " + proveedor.getRating());

        Proveedor proveedor1 = proveedorService.getProveedorById(idProveedor);
       // Calificacion calificacion = restTemplate.getForObject("http://ms-calificacion/calificacion/"+ idProveedor+"/proveedor", Calificacion.class);
        if(proveedor1==null){
            return new ResponseEntity<>("No existe un Proveedor correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        proveedor1.setIdProvider(idProveedor);
        proveedor1.setRating(proveedor.getRating());
        return new ResponseEntity<>(proveedorService.update(idProveedor, proveedor1),HttpStatus.OK);
    }

    @KafkaListener(topics = "calificacion", groupId = "proveedor")
    public Calificacion consumerCalificacion (Calificacion proveedor){
        System.out.println(" Mensaje entrante de un proveedor = " + proveedor);
        return proveedor;
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

    /*@PostMapping("/proveedor")
    public Proveedor saveProveedor(@RequestBody Proveedor proveedor) {
        System.out.println("proveedor = " + proveedor.getContacto());
        return proveedorRepository.save(proveedor);
    }
    @GetMapping("/proveedor/{id}")
    public Proveedor getProveedor(@PathVariable("id") String idProveedor){
        return proveedorRepository.getProveedorById(idProveedor);
    }

     @PutMapping("/proveedor/{id}")
    public String updateProveedor(@PathVariable("id")String idProveedor,@RequestBody Proveedor proveedor){
        return proveedorRepository.update(idProveedor, proveedor);
    }

    */

    /*

    @GetMapping("/findAllByProveedor")
    public ResponseEntity <?> findAllByEps() throws Exception {
        List <Map<String, String>> list = proveedorService.findAllByEps();
        if(!(list == null)){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") Short id ) throws Exception {
        Proveedor proveedor = proveedorService.findById(id);
        if(proveedor==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(proveedorService.findById(id),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity <?> create(@RequestBody Proveedor proveedor) throws Exception {
        return new ResponseEntity<>(proveedorService.create(proveedor), HttpStatus.OK);
    }

    }*/

}

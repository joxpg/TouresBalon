package co.com.puj.aes.pagos.controller;

import co.com.puj.aes.pagos.entity.Pagos;
import co.com.puj.aes.pagos.service.PagosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/pagos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class PagosController {
    @Autowired
    private PagosService pagosService;

    public PagosController(PagosService pagosService){this.pagosService = pagosService;}

    @Autowired
    private KafkaTemplate<String, Pagos> kafkaTemplate;
    private static final String TOPIC = "pagos";

    @GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<Pagos> list = pagosService.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Pagos pagos) throws Exception {
        return new ResponseEntity<>(pagosService.save(pagos), HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") String id ) throws Exception {
        Pagos pagos = pagosService.getPagoById(id);
        if(pagos==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        kafkaTemplate.send(TOPIC, pagos);
        return new ResponseEntity<>(pagosService.getPagoById(id),HttpStatus.OK);
    }
    @KafkaListener(topics = "pagos", groupId = "pagos")
    public String consumerPagos (String pagos){
        return pagos;
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") String idPagos, @RequestBody Pagos pagos) throws Exception {
        Pagos pagos1 = pagosService.getPagoById(idPagos);
        if(pagos1==null){
            return new ResponseEntity<>("No existe un pago correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        pagos.setIdPagos(idPagos);
        return new ResponseEntity<>(pagosService.update(idPagos, pagos),HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("{idProveedor}")
    public ResponseEntity <?> delete(@PathVariable("idPagos") String idPagos) throws Exception {
        Pagos pagos = pagosService.getPagoById(idPagos);
        if(pagos==null){
            return new ResponseEntity<>("No existe un pago correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pagosService.deletePagos(idPagos), HttpStatus.OK);
    }
}


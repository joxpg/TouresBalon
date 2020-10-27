package co.com.puj.aes.reserva.controller;

import co.com.puj.aes.reserva.entity.Reserva;
import co.com.puj.aes.reserva.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/reserva")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class ReservaController {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private KafkaTemplate<String, Reserva> kafkaTemplate;
    private static final String TOPIC = "reserva";

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Reserva reserva) throws Exception {
        return new ResponseEntity<>(reservaRepository.save(reserva), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") String id) throws Exception {

        System.out.println(" llegando a getbyId" );

        Reserva reserva = reservaRepository.getProveedorById(id);
        if(reserva ==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }

        kafkaTemplate.send(TOPIC, reserva);
        return new ResponseEntity<>(reservaRepository.getProveedorById(id),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") String idReserva, @RequestBody Reserva reserva) throws Exception {
        Reserva reserva1 = reservaRepository.getProveedorById(idReserva);
        if(reserva1 ==null){
            return new ResponseEntity<>("No existe un Reserva correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        reserva.setIdReserva(idReserva);
        return new ResponseEntity<>(reservaRepository.update(idReserva, reserva),HttpStatus.OK);
    }
    @ResponseBody
    @DeleteMapping("{idReserva}")
    public ResponseEntity <?> delete(@PathVariable("idReserva") String idReserva) throws Exception {
        Reserva reserva = reservaRepository.getProveedorById(idReserva);
        if(reserva ==null /*|| !reservaService.existeById(id)*/){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservaRepository.delete(idReserva), HttpStatus.OK);
    }


    @KafkaListener(topics = "reserva", groupId = "reserva"/*,    containerFactory = "reservaKafkaListenerFactory"*/)
    public String consumerProveedor (String proveedor){
        System.out.println(" Mensaje entrante de un proveedor = " + proveedor);
        return proveedor;
    }

}

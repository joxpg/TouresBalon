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

import java.util.List;


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

    @GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<Reserva> list = reservaRepository.getlist();
        if(!list.isEmpty()){
            //return new ResponseEntity<List>(list, HttpStatus.OK);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Reserva reserva) throws Exception {
    Reserva booking =reservaRepository.save(reserva);
    kafkaTemplate.send(TOPIC, booking);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") String id) throws Exception {
        Reserva reserva = reservaRepository.getProveedorById(id);
        if(reserva ==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservaRepository.getProveedorById(id),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") String idBooking, @RequestBody Reserva reserva) throws Exception {
        Reserva reserva1 = reservaRepository.getProveedorById(idBooking);
        if(reserva1 ==null){
            return new ResponseEntity<>("No existe un Reserva correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        reserva.setIdBooking(idBooking);
        return new ResponseEntity<>(reservaRepository.update(idBooking, reserva),HttpStatus.OK);
    }
    @ResponseBody
    @DeleteMapping("{idBooking}")
    public ResponseEntity <?> delete(@PathVariable("idBooking") String idBooking) throws Exception {
        Reserva reserva = reservaRepository.getProveedorById(idBooking);
        if(reserva ==null /*|| !reservaService.existeById(id)*/){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservaRepository.delete(idBooking), HttpStatus.OK);
    }


    @KafkaListener(topics = "reserva", groupId = "reserva")
    public String consumerProveedor (String proveedor){
        System.out.println(" Mensaje entrante de un proveedor = " + proveedor);
        return proveedor;
    }

}

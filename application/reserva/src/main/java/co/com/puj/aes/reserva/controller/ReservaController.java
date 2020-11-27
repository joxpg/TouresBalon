package co.com.puj.aes.reserva.controller;

import co.com.puj.aes.msBusqueda.Entity.BusquedaReserva;
import co.com.puj.aes.msPagos.entity.UpdateReserva;
import co.com.puj.aes.reserva.entity.Aprobacion;
import co.com.puj.aes.reserva.entity.Reserva;
import co.com.puj.aes.reserva.service.AprobacionService;
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
    private AprobacionService aprobacionRepository;
    @Autowired
    private KafkaTemplate<Object, BusquedaReserva> kafkaTemplate;
    private static final String TOPIC = "reserva";

    @GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<Reserva> list = reservaRepository.getlist();
        if(!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Reserva reserva) throws Exception {
        return new ResponseEntity<>(reservaRepository.save(reserva), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") String id) throws Exception {
        Reserva reserva = reservaRepository.getReservaById(id);
        if(reserva ==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservaRepository.getReservaById(id),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") String idBooking, @RequestBody Reserva reserva) throws Exception {
        Reserva reserva1 = reservaRepository.getReservaById(idBooking);
        if(reserva1 ==null){
            return new ResponseEntity<>("No existe un Reserva correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        reserva.setIdBooking(idBooking);
        return new ResponseEntity<>(reservaRepository.update(idBooking, reserva),HttpStatus.OK);
    }
    @ResponseBody
    @DeleteMapping("{idBooking}")
    public ResponseEntity <?> delete(@PathVariable("idBooking") String idBooking) throws Exception {
        Reserva reserva = reservaRepository.getReservaById(idBooking);
        if(reserva ==null /*|| !reservaService.existeById(id)*/){
            return new ResponseEntity<>("No existe un reserva correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservaRepository.delete(idBooking), HttpStatus.OK);
    }

    @KafkaListener(topics = "reserva", groupId = "reserva")
    public BusquedaReserva consumerReserva(BusquedaReserva reserva) throws Exception {
        BusquedaReserva booking = reservaRepository.savePagoReserva(reserva);

        //TODO Construir validaciones pendientes según categoría
        if (reserva.getCategory().equals("Dorado")){
            Aprobacion aprobacion = new Aprobacion();
            aprobacion.setIdBooking(reserva.getIdBooking());
            aprobacion.setStatus(reserva.getStatus());
            createAprobacion(aprobacion);
        }
        return reserva;
    }
    public Aprobacion createAprobacion (Aprobacion aprobacion) throws Exception {
        Aprobacion aprobacion1 = aprobacionRepository.save(aprobacion);
        return aprobacion1;
    }

   public Aprobacion updateReserva (Aprobacion aprobacion) throws Exception {
        return reservaRepository.saveAprobacion(aprobacion);
    }

    @KafkaListener(topics = "confirmarreserva", groupId = "reserva")
    public Reserva consumerUpdatePagoReserva (UpdateReserva reserva) throws Exception {
       Reserva reserva1 = reservaRepository.getReservaById(reserva.getIdBooking());
       reserva1.setActive(reserva.isActive());
       reservaRepository.update(reserva1.getIdBooking(), reserva1);
       return reserva1;
    }
}

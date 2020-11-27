package co.com.puj.aes.reserva.controller;

import co.com.puj.aes.msBusqueda.Entity.BusquedaReserva;
import co.com.puj.aes.reserva.entity.Aprobacion;
import co.com.puj.aes.reserva.entity.Reserva;
import co.com.puj.aes.reserva.repository.AprobacionRepository;
import co.com.puj.aes.reserva.service.AprobacionService;
import co.com.puj.aes.reserva.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/aprobacion")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class AprobacionController {

    @Autowired
    private AprobacionService aprobacionRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private AprobacionService aprobacionService;

    @Autowired
    private KafkaTemplate<Object, Reserva> kafkaTemplate;
    //private static final String TOPIC = "reserva";

    private static final String STATUS_DEFAULT = "Pending";


    @GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<Aprobacion> list = aprobacionRepository.getlist();
        if(!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Aprobacion aprobacion) throws Exception {
        return new ResponseEntity<>(aprobacionRepository.save(aprobacion), HttpStatus.OK);
    }

    /**
     *
     * @param idBooking id de la reserva
     * @return los datos de aprobación de una reserva por id de reserva
     * @throws Exception
     */

    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> findByIdBooking(@PathVariable("id") String idBooking) throws Exception {
        Aprobacion aprobacion = aprobacionService.findByIdBooking(idBooking);
        if(aprobacion ==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(aprobacionService.findByIdBooking(idBooking),HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") String idBooking, @RequestBody Aprobacion aprobacion) throws Exception {
        Aprobacion aprobacion1 = aprobacionRepository.findByIdBooking(idBooking);
        if(aprobacion1 ==null){
            return new ResponseEntity<>("No existe un Solicitud pendiente de Aprobación  correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        //aprobacion.setIdBooking(idBooking);
        aprobacionRepository.update(idBooking, aprobacion);

        Reserva reserva = reservaRepository.getReservaById(idBooking);
        reserva.setStatus(aprobacion.getStatus());
        kafkaTemplate.send("pagopendiente", reserva);

        return new ResponseEntity<>(reservaRepository.update(idBooking, reserva),HttpStatus.OK);
    }
    @ResponseBody
    @DeleteMapping("{idBooking}")
    public ResponseEntity <?> delete(@PathVariable("idBooking") String idBooking) throws Exception {
        Aprobacion aprobacion = aprobacionRepository.getAprobacionById(idBooking);
        if(aprobacion ==null /*|| !reservaService.existeById(id)*/){
            return new ResponseEntity<>("No existe una Aprobación correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(aprobacionRepository.deleteAprobacion(idBooking), HttpStatus.OK);
    }



}

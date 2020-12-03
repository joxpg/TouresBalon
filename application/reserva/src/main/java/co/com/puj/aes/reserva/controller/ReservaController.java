package co.com.puj.aes.reserva.controller;

import co.com.puj.aes.msBusqueda.Entity.BusquedaReserva;
import co.com.puj.aes.msPagos.entity.UpdateReserva;
import co.com.puj.aes.reserva.entity.Aprobacion;
import co.com.puj.aes.reserva.entity.Reserva;
import co.com.puj.aes.reserva.service.AprobacionService;
import co.com.puj.aes.reserva.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/reserva")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class ReservaController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private AprobacionService aprobacionRepository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Qualifier("getWebClientBuilder")
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private KafkaTemplate<Object, BusquedaReserva> kafkaTemplate;
    private static final String TOPIC = "reserva";

    @GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<Reserva> list = reservaService.getlist();
        if(!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Reserva reserva) throws Exception {
        reserva.setStatus("Pending");
        long H = (reserva.getShowBooking().getAmountS() != null ? reserva.getShowBooking().getAmountS() : 0);
        long S = (reserva.getHostingBooking().getAmountH() != null ? reserva.getHostingBooking().getAmountH() : 0);
        long T = (reserva.getTransportBooking().getAmountT() != null ? reserva.getTransportBooking().getAmountT() : 0);
        long amount = (H+S+T);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("category", reserva.getCategory());
        personJsonObject.put("amount", amount);

        try {
            HttpEntity<String> request =
                    new HttpEntity<String>(personJsonObject.toString(),headers);

            Object validar = restTemplate.postForObject("http://reglasdenegocio/orden",
                    request, Object.class);
            System.out.println(" Impresion de Validar " + validar.toString());

            if (validar.equals(true)){
                reservaService.save(reserva);
                Aprobacion aprobacion = new Aprobacion();
                System.out.println("reserva.getIdBooking() = " + reserva.getIdBooking());
                aprobacion.setIdBooking(reserva.getIdBooking());
                aprobacion.setStatus(reserva.getStatus());
                createAprobacion(aprobacion);
            } else {
                reserva.setStatus("Approved");
                reservaService.save(reserva);
            }
        } catch (Exception e){

            System.out.println("exeption  = " + e);
        }
        return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") String id) throws Exception {
        Reserva reserva = reservaService.getReservaById(id);
        if(reserva ==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservaService.getReservaById(id),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") String idBooking, @RequestBody Reserva reserva) throws Exception {
        Reserva reserva1 = reservaService.getReservaById(idBooking);
        if(reserva1 ==null){
            return new ResponseEntity<>("No existe un Reserva correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        reserva.setIdBooking(idBooking);
        return new ResponseEntity<>(reservaService.update(reserva),HttpStatus.OK);
    }
    @ResponseBody
    @DeleteMapping("{idBooking}")
    public ResponseEntity <?> delete(@PathVariable("idBooking") String idBooking) throws Exception {
        Reserva reserva = reservaService.getReservaById(idBooking);
        if(reserva ==null /*|| !reservaService.existeById(id)*/){
            return new ResponseEntity<>("No existe un reserva correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reservaService.deleteReserva(idBooking), HttpStatus.OK);
    }

    @KafkaListener(topics = "reserva", groupId = "reserva")
    public BusquedaReserva consumerReserva(BusquedaReserva reserva) throws Exception {
        System.out.println("entrando a reserva desde Busqueda = " + reserva);
        reserva.setStatus("Pending");
        long H = (reserva.getShowBooking().getAmountS() != null ? reserva.getShowBooking().getAmountS() : 0);
        long S = (reserva.getHostingBooking().getAmountH() != null ? reserva.getHostingBooking().getAmountH() : 0);
        long T = (reserva.getTransportBooking().getAmountT() != null ? reserva.getTransportBooking().getAmountT() : 0);
        long amount = (H+S+T);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("category", reserva.getCategory());
        personJsonObject.put("amount", amount);

        try {
            HttpEntity<String> request =
                    new HttpEntity<String>(personJsonObject.toString(),headers);

            Object validar = restTemplate.postForObject("http://reglasdenegocio/orden",
                    request, Object.class);
            System.out.println(" Impresion de Validar " + validar.toString());

            if (validar.equals(true)){
                Aprobacion aprobacion = new Aprobacion();
                reservaService.savePagoReserva(reserva);
                System.out.println("reserva.getIdBooking() = " + reserva.getIdBooking());
                aprobacion.setIdBooking(reserva.getIdBooking());
                aprobacion.setStatus(reserva.getStatus());
                createAprobacion(aprobacion);
            } else {
                reserva.setStatus("Approved");
                reservaService.savePagoReserva(reserva);
                kafkaTemplate.send("pagopendiente", reserva);
            }
        } catch (Exception e){

            System.out.println("exeption  = " + e);
        }
        return reserva;
    }
    public Aprobacion createAprobacion (Aprobacion aprobacion) throws Exception {
        Aprobacion aprobacion1 = aprobacionRepository.save(aprobacion);
        return aprobacion1;
    }

   public Aprobacion updateReserva (Aprobacion aprobacion) throws Exception {
        return reservaService.saveAprobacion(aprobacion);
    }

    @KafkaListener(topics = "confirmarreserva", groupId = "reserva")
    public Reserva consumerUpdatePagoReserva (UpdateReserva reserva) throws Exception {
       Reserva reserva1 = reservaService.getReservaById(reserva.getIdBooking());
       reserva1.setActive(reserva.isActive());
      reservaService.update(reserva1);
       return reserva1;
    }
}

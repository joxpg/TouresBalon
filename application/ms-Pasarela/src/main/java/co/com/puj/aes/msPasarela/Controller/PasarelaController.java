package co.com.puj.aes.msPasarela.Controller;

import co.com.puj.aes.msBusqueda.Entity.BusquedaReserva;
import co.com.puj.aes.reserva.entity.Reserva;
import  co.com.puj.aes.msPasarela.Entity.Pasarela;
import co.com.puj.aes.msPasarela.Service.PasarelaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/pasarela")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class PasarelaController {
    @Autowired
    private KafkaTemplate<String, Pasarela> kafkaTemplate;
    //private static final String TOPIC = "pagos";
    @Autowired
    PasarelaService pasarelaService;
    public PasarelaController(PasarelaService pasarelaService) {this.pasarelaService = pasarelaService;}

    /*@PostMapping("")
    public void servicioPagos(@Valid @RequestBody Pasarela pasarela){
        kafkaTemplate.send("pagosresultado", pasarela);
    }*/
    @KafkaListener(topics = "pagopendiente", groupId = "pagopendiente")
    @PostMapping("")
    public BusquedaReserva consumerReserva(BusquedaReserva reserva){
        System.out.println(" Mensaje entrante de pago de reserva = " + reserva);
        Pasarela pasarela= new Pasarela();
        pasarela.setIdBooking(reserva.getIdBooking());
        pasarela.setActive(true);
        pasarela.setAmountH(reserva.getHostingBooking().getAmountH());
        pasarela.setAmountS(reserva.getShowBooking().getAmountS());
        pasarela.setAmountT(reserva.getTransportBooking().getAmountT());
        kafkaTemplate.send("pagosresultado", pasarela);
        return reserva;
    }
}

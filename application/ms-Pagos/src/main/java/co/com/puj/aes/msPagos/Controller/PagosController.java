package co.com.puj.aes.msPagos.Controller;

import co.com.puj.aes.msPagos.entity.Pagos;
import co.com.puj.aes.msPasarela.Entity.Pasarela;
import co.com.puj.aes.msPagos.entity.UpdateReserva;
import co.com.puj.aes.msPagos.service.PagosService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/pagos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class PagosController {
    @Autowired
    private KafkaTemplate<String, Pagos> kafkaTemplatepagos;
    @Autowired
    private KafkaTemplate<String, UpdateReserva> kafkaTemplatereserva;
    @Autowired
    private PagosService pagosService;

    public PagosController(PagosService pagosService) {this.pagosService = pagosService;}

    /*@PostMapping("")
    public void servicioReserva( @RequestBody UpdateReserva updateReserva){
        kafkaTemplatereserva.send("confirmarreserva", updateReserva);
    }*/

    @KafkaListener(topics = "pagosresultado", groupId = "pagosresultado")
    @PostMapping("")
    public Pasarela consumerPasarela( Pasarela pago){
        System.out.println(" Mensaje entrante de un pago = " + pago);
        UpdateReserva updateReserva= new UpdateReserva();
        updateReserva.setIdBooking(pago.getIdBooking());
        updateReserva.setActive(pago.getActive());
        kafkaTemplatereserva.send("confirmarreserva", updateReserva);
        System.out.println(" Mensaje saliente de un pago = " + updateReserva);
        return pago;
    }

    @PostMapping(value = "cancelacion/{idBooking}")
    public void servicioCancelacion(@PathVariable("idBooking") String idBooking) throws Exception {
        UpdateReserva updateReserva= new UpdateReserva();
        updateReserva.setIdBooking(idBooking);
        updateReserva.setActive(false);
        kafkaTemplatereserva.send("cancelacion", updateReserva);
    }



}

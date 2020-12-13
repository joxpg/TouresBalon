package co.com.puj.aes.msPagos.Controller;

import co.com.puj.aes.msPagos.entity.Pagos;
import co.com.puj.aes.msPasarela.Entity.Pasarela;
import co.com.puj.aes.msPagos.entity.UpdateReserva;
import co.com.puj.aes.msPagos.service.PagosService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Pasarela consumerPasarela( @RequestBody Pasarela pago )throws Exception{
        System.out.println(" Mensaje entrante de un pago = " + pago);
        UpdateReserva updateReserva= new UpdateReserva();
        updateReserva.setIdBooking(pago.getIdBooking());
        updateReserva.setActive(pago.getActive());
        kafkaTemplatereserva.send("confirmarreserva", updateReserva);
        System.out.println(" Mensaje saliente de un pago = " + updateReserva);
        return pago;
        /*Pagos pago1= new Pagos();
        pago1.setIdBooking(pago.getIdBooking());
        pago1.setAmountH(pago.getAmountH());
        pago1.setAmountT(pago.getAmountT());
        pago1.setAmountS(pago.getAmountS());
        pago1.setActive(pago.getActive());
        System.out.println(" Guardando = " + pago1);
        return pagosService.create(pago1);*/
    }


    @PutMapping(value = "cancelacion/{idBooking}")
    public void servicioCancelacion(@PathVariable("idBooking") String idBooking) throws Exception {
        UpdateReserva updateReserva= new UpdateReserva();
        updateReserva.setIdBooking(idBooking);
        updateReserva.setActive(false);
        kafkaTemplatereserva.send("cancelacion", updateReserva);
        System.out.println(" Mensaje saliente de cancelacion = " + updateReserva);
        /*Pagos pagos= pagosService.findByIdBooking(idBooking);
        if(pagos==null){
            return "No existe un pago correspondiente a esa reserva";
        }
        pagos.setActive(false);
        return pagosService.update(pagos);*/
    }



}

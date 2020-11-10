package co.com.puj.aes.msPagos.Controller;

import co.com.puj.aes.msPagos.entity.Pagos;
import co.com.puj.aes.msPasarela.Entity.Pasarela;
import co.com.puj.aes.msPagos.entity.UpdateReserva;
import co.com.puj.aes.msPagos.service.PagosService;
import co.com.puj.aes.msPagos.repository.PagosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    @PostMapping("")
    public void servicioReserva( @RequestBody UpdateReserva updateReserva){
        kafkaTemplatereserva.send("confirmarreserva", updateReserva);
    }

    @KafkaListener(topics = "pagosresultado", groupId = "pagosresultado")
    public Pasarela consumerPasarela( Pasarela pago){
        System.out.println(" Mensaje entrante de un pago = " + pago);
        return pago;
    }



}

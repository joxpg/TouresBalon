package co.com.puj.aes.calificacion.controller;

import co.com.puj.aes.calificacion.entity.Calificacion;
import co.com.puj.aes.calificacion.entity.HistorialCalificacion;
import co.com.puj.aes.calificacion.service.CalificacionService;
import co.com.puj.aes.calificacion.service.HistorialCalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/historicocalificacion")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class HistorialCalificacionController {

    @Autowired
    private HistorialCalificacionService historialCalificacionService;
    public HistorialCalificacionController(HistorialCalificacionService historialCalificacionService) {this.historialCalificacionService = historialCalificacionService;}

    @Autowired
    private KafkaTemplate<String, Calificacion> kafkaTemplate;
    private static final String TOPIC = "reserva";

    @GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<HistorialCalificacion> list = historialCalificacionService.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }
    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") Short id ) throws Exception {
        HistorialCalificacion historialCalificacion = historialCalificacionService.findById(id);
        if(historialCalificacion==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(historialCalificacionService.findById(id),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity <?> create(@RequestBody HistorialCalificacion historialCalificacion) throws Exception {
        return new ResponseEntity<>(historialCalificacionService.create(historialCalificacion), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") Short id, @RequestBody HistorialCalificacion historialCalificacion) throws Exception {
        HistorialCalificacion historialCalificacion1 = historialCalificacionService.findById(id);
        if(historialCalificacion1==null){
            return new ResponseEntity<>("No existe una Calificación correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        historialCalificacion.setId(id);
        return new ResponseEntity<>(historialCalificacionService.update(historialCalificacion),HttpStatus.OK);
    }
}

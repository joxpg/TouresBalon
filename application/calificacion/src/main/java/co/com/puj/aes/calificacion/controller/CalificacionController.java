package co.com.puj.aes.calificacion.controller;
import co.com.puj.aes.calificacion.entity.Calificacion;
import co.com.puj.aes.calificacion.service.CalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/calificacion")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class CalificacionController {
    @Autowired
    private CalificacionService calificacionService;

    public CalificacionController(CalificacionService calificacionService) {this.calificacionService = calificacionService;}

@Autowired
    private KafkaTemplate<String, Calificacion> kafkaTemplate;
    private static final String TOPIC = "reserva";

    @GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<Calificacion> list = calificacionService.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }
    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") Short id ) throws Exception {
        Calificacion calificacion = calificacionService.findById(id);
        if(calificacion==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(calificacionService.findById(id),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity <?> create(@RequestBody Calificacion calificacion) throws Exception {
        return new ResponseEntity<>(calificacionService.create(calificacion), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") Short id, @RequestBody Calificacion calificacion) throws Exception {
        Calificacion calificacion1 = calificacionService.findById(id);
        if(calificacion1==null){
            return new ResponseEntity<>("No existe una Calificación correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        calificacion.setIdCalificacion(id);
        return new ResponseEntity<>(calificacionService.update(calificacion),HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public ResponseEntity <?> delete(@PathVariable("id") Short id) throws Exception {
        Calificacion calificacion1 = calificacionService.findById(id);
        if(calificacion1==null || !calificacionService.existeById(id)){
            return new ResponseEntity<>("No existe una Calificacion correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(calificacionService.deleteCalificacion(id), HttpStatus.OK);
    }
    @Deprecated
    @PutMapping("/activar/{id}")
    public ResponseEntity <?> activar(@PathVariable("id") Short id) throws Exception {
        Calificacion eps = calificacionService.findId(id);
        if(eps==null || !calificacionService.existeById(id)){
            return new ResponseEntity<>("No existe un Eps correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(calificacionService.activar(id), HttpStatus.OK);
    }


}

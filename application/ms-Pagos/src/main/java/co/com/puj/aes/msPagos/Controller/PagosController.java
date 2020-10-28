package co.com.puj.aes.msPagos.Controller;

import co.com.puj.aes.msPagos.entity.Pagos;
import co.com.puj.aes.msPagos.service.PagosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/pagos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class PagosController {

    @Autowired
    private PagosService pagosService;

    public PagosController(PagosService pagosService) {this.pagosService = pagosService;}

    @GetMapping("")
    public ResponseEntity<?> getList() throws Exception {
        List<Pagos> list = pagosService.findAll();
        if(!list.isEmpty()){
            return new ResponseEntity<List>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findAllByPagos")
    public ResponseEntity <?> findAllByPagos() throws Exception {
        List <Map<String, String>> list = pagosService.findAllByPagos();
        if(!(list == null)){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay registros",HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @GetMapping("{id}")
    public ResponseEntity <?> getByid(@PathVariable("id") Short id ) throws Exception {
        Pagos pagos = pagosService.findById(id);
        if(pagos==null){
            return new ResponseEntity<>("No existen resultados para su consulta",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pagosService.findById(id),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity <?> create(@RequestBody Pagos pagos) throws Exception {
        return new ResponseEntity<>(pagosService.create(pagos), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity <?> update(@PathVariable("id") Short id, @RequestBody Pagos pagos) throws Exception {
        Pagos pagos1 = pagosService.findById(id);
        if(pagos1==null){
            return new ResponseEntity<>("No existe un pago correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }
        pagos.setIdPagos(id);
        return new ResponseEntity<>(pagosService.update(pagos),HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public ResponseEntity <?> delete(@PathVariable("id") Short id) throws Exception {
        Pagos pagos1 = pagosService.findById(id);
        if(pagos1==null || !pagosService.existeById(id)){
            return new ResponseEntity<>("No existe un pago correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(pagosService.deletePagos(id), HttpStatus.OK);
    }
    @Deprecated
    @PutMapping("/activar/{id}")
    public ResponseEntity <?> activar(@PathVariable("id") Short id) throws Exception {
        Pagos pagos = pagosService.findId(id);
        if(pagos==null || !pagosService.existeById(id)){
            return new ResponseEntity<>("No existe un pago correspondiente al id ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(pagosService.activar(id), HttpStatus.OK);
    }


}

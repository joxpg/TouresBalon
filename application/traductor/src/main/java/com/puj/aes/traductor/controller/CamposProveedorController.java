package com.puj.aes.traductor.controller;

import com.puj.aes.traductor.entity.CamposProveedor;
import com.puj.aes.traductor.entity.Peticion;
import com.puj.aes.traductor.service.CamposProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping (value = "/traductor")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class CamposProveedorController {
    @Autowired
    private CamposProveedorService camposProveedorService;

    public CamposProveedorController(CamposProveedorService camposProveedorService){this.camposProveedorService = camposProveedorService;}

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CamposProveedor camposProveedor) throws Exception {
        return new ResponseEntity<>(camposProveedorService.save(camposProveedor), HttpStatus.OK);
    }

    @GetMapping("/proveedor/{idProvider}")
    public ResponseEntity<?> translate(@RequestBody Peticion peticion, @PathVariable String idProvider)throws Exception {
        return new ResponseEntity<>(camposProveedorService.traducirPeticion(peticion, idProvider), HttpStatus.OK);
    }

}

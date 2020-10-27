package com.puj.aes.producto.producto.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Busqueda implements Serializable {
    private Long id;
    private String idProveedor;
    private Long idProd;
    private Date fechaInicio;
    private Date fechaFin;
    private Short tipoProducto;
    private String categoria;
    private String origen;
    private String destino;
    private Double precio;
    private Double pesoConvenio;


}

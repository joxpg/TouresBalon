package co.com.puj.aes.msPasarela.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Pasarela implements Serializable {
    private Long id;
    private String nombre;
    private Short tipoPruducto;
    private String categoria;
    private String descripcion;
    private String infoAdicional;
    private Date fechaInicio;
    private Date fechaFin;
    private String origen;
    private String destino;
    private File imagen;
    private Double precio;
    private String codigoPromocional;
    private Long idBusqueda;
    private Double calificacion;
    private Double pesoConvenio;
    private String idProveedor;
}

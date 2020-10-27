package co.com.puj.aes.calificacion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Johan Céspedes at PUJ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"HISTORIAL_CALIFICACION\"", schema = "\"calificacionSch\"")
@NamedQueries({
        @NamedQuery(name = "HistorialCalificacion.findAll", query = "SELECT e FROM Calificacion e")})

public class HistorialCalificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Short id;

    @Size(max = 200)
    @Column(name = "ID_CLIENTE")
    private String idCliente;

    @Size(max = 200)
    @Column(name = "ID_PROVEEDOR")
    private String idProveedor;
    @Column(name = "CALIFICACION")
    private short calificacion;
    @Column(name = "FECHA_CALIFICACION")
    private LocalDate fechaCalificacion;

    @Column(name = "ESTADO_CALIFICACION")
    private boolean estadoCalificacion;

    public HistorialCalificacion (Short id) {this.id = id;}
}

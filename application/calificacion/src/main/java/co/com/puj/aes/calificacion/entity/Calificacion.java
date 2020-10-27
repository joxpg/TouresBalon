package co.com.puj.aes.calificacion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Johan Céspedes at PUJ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"CALIFICACION\"", schema = "\"calificacionSch\"")
@NamedQueries({
        @NamedQuery(name = "Calificacion.findAll", query = "SELECT e FROM Calificacion e")})

public class Calificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CALIFICACION")
    private Short idCalificacion;
    @Size(max = 100)
    @Column(name = "ID_PROVEEDOR")
    private String idProveedor;
    @Size(max = 100)
    @Column(name = "CALIFICACION")
    private short calificacion;
    @Column(name = "ESTADO_CALIFICACION")
    private boolean estadoCalificacion;

    public Calificacion (Short idCalificacion) {this.idCalificacion = idCalificacion;}




}

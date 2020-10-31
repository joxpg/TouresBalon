/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.puj.aes.msPagos.entity;

import lombok.*;

import java.io.Serializable;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"PAGOS\"")
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT e FROM Pagos e")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PAGOS")
    private Short idPagos;
    @Column(name = "ID_RESERVA")
    private String idReserva;
    @Column(name = "MONTO")
    private String monto;
    @Column(name = "ESTADO_PAGOS")
    private Boolean estadoPagos;


    public Pagos(Short idPagos) {
        this.idPagos = idPagos;
    }

    public void setEstadoPagos(boolean b) {this.estadoPagos = estadoPagos; }

    public void setIdPagos(Short idPagos) { this.idPagos = idPagos; }

    public Short getIdPagos() {return idPagos; }

    public void setIdReserva(String idPagos) { this.idReserva = idReserva; }

    public String getIdReserva() {return idReserva; }
}

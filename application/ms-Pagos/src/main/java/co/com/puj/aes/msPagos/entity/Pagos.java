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
    private int idPayment;
    @Column(name = "ID_RESERVA")
    private String idBooking;
    @Column(name = "PAGOT")
    private Long amountT;
    @Column(name = "PAGOH")
    private Long amountH;
    @Column(name = "PAGOS")
    private Long amountS;
    @Column(name = "ESTADO_RESERVA")
    private Boolean active;

    public int getIdPayment() { return idPayment; }
    public void setIdPayment(int idPayment) { this.idPayment = idPayment;}

    public String getIdBooking() { return idBooking; }
    public void setIdBooking(String idBooking) { this.idBooking = idBooking;}

    public Long getAmountT() { return amountT; }
    public void setAmountT(Long amountT) { this.amountT = amountT;}

    public Long getAmountH() { return amountH; }
    public void setAmountH(Long amountH) { this.amountH = amountH;}

    public Long getAmountS() { return amountS; }
    public void setAmountS(Long amountS) { this.amountS = amountS;}

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active;}



}

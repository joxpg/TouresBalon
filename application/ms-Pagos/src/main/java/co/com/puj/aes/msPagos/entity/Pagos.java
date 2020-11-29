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
    private String idPayment;
    @Column(name = "ID_RESERVA")
    private String idBooking;
    @Column(name = "PAGOT")
    private String amountT;
    @Column(name = "PAGOH")
    private String amountH;
    @Column(name = "PAGOS")
    private String amountS;
    @Column(name = "ESTADO_RESERVA")
    private Boolean active;

}

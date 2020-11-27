/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.puj.aes.reserva.entity;

import co.com.puj.aes.reserva.entity.Espectaculo;
import co.com.puj.aes.reserva.entity.Hospedaje;
import co.com.puj.aes.reserva.entity.Trasporte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Johan Céspedes at PUJ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    private String idBooking;
    private String idCoustumer;
    private String email;
    private String address;
    private String phoneNumber;
    private String contact;
    private boolean active;
    private String status;
    private String category;
    private String country;
    private Trasporte transportBooking;
    private Hospedaje hostingBooking;
    private Espectaculo showBooking;

    public void setIdBooking(String idBooking) { this.idBooking = idBooking; }
    public String getIdBooking() { return idBooking; }

    public void setActive(Boolean active) { this.active = active; }
    public Boolean getStatus() { return active; }

    public Hospedaje getHostingBooking() { return hostingBooking; }
    public Espectaculo getShowBooking() { return showBooking; }
    public Trasporte getTransportBooking() { return transportBooking; }

}

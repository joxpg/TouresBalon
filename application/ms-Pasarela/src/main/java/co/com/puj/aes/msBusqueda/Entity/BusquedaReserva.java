package co.com.puj.aes.msBusqueda.Entity;

import co.com.puj.aes.reserva.entity.Espectaculo;
import co.com.puj.aes.reserva.entity.Hospedaje;
import co.com.puj.aes.reserva.entity.Trasporte;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class BusquedaReserva implements Serializable {
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
    private BusquedaTrasporte transportBooking;
    private BusquedaHospedaje hostingBooking;
    private BusquedaEspectaculo showBooking;

    public void setIdBooking(String idBooking) { this.idBooking = idBooking; }
    public String getIdBooking() { return idBooking; }

    public void setActive(Boolean active) { this.active = active; }
    public Boolean getStatus() { return active; }

    public BusquedaHospedaje getHostingBooking() { return hostingBooking; }
    public BusquedaEspectaculo getShowBooking() { return showBooking; }
    public BusquedaTrasporte getTransportBooking() { return transportBooking; }
}

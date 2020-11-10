package co.com.puj.aes.msBusqueda.Entity;

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
    private String country;
    private BusquedaTrasporte transportBooking;
    private BusquedaHospedaje hostingBooking;
    private BusquedaEspectaculo showBooking;
}

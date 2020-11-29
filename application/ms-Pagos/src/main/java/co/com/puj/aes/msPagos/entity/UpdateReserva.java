package co.com.puj.aes.msPagos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UpdateReserva implements Serializable {
    private String idBooking;
    private Boolean active;

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }

    public String getIdBooking() {
        return idBooking;
    }
    public Boolean getActive() {
        return active;
    }
}

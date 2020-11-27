package co.com.puj.aes.msPasarela.Entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Pasarela implements Serializable {

    private String idPayment;
    private String idBooking;
    private String amountT;
    private String amountH;
    private String amountS;
    private Boolean active;


    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }

    public String getIdBooking() {
        return idBooking;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    public Boolean getActive() {
        return active;
    }
}


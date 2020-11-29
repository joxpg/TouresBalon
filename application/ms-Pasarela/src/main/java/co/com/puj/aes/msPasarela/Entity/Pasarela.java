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

    public String getIdPayment() { return idPayment; }
    public void setIdPayment(String idPayment) { this.idPayment = idPayment; }

    public String getIdBooking() { return idBooking; }
    public void setIdBooking(String idBooking) { this.idBooking = idBooking; }

    public void setActive(Boolean active) { this.active = active; }
    public Boolean getStatus() { return active; }

    public String getAmountH() { return amountH; }
    public void setAmountH(String amountH) { this.amountH = amountH; }

    public String getAmountT() { return amountT; }
    public void setAmountT(String amountT) { this.amountT = amountT; }

    public String getAmountS() { return amountS; }
    public void setAmountS(String amountS) { this.amountS = amountS; }
}

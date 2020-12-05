package co.com.puj.aes.msPasarela.Entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Pasarela implements Serializable {

    private int idPayment;
    private String idBooking;
    private Long amountT;
    private Long amountH;
    private Long amountS;
    private Boolean active;

    public int getIdPayment() { return idPayment; }
    public void setIdPayment(int idPayment) { this.idPayment = idPayment; }

    public String getIdBooking() { return idBooking; }
    public void setIdBooking(String idBooking) { this.idBooking = idBooking; }

    public void setActive(Boolean active) { this.active = active; }
    public Boolean getStatus() { return active; }

    public Long getAmountH() { return amountH; }
    public void setAmountH(Long amountH) { this.amountH = amountH; }

    public Long getAmountT() { return amountT; }
    public void setAmountT(Long amountT) { this.amountT = amountT; }

    public Long getAmountS() { return amountS; }
    public void setAmountS(Long amountS) { this.amountS = amountS; }
}

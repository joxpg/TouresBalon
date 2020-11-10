package co.com.puj.aes.msPasarela.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

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
}

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
    private String idBooking;
    private String idCoustumer;
    private String email;
    private String address;
    private String phoneNumber;
    private String contact;
    private boolean active;
    private String country;
    private String amount;
}

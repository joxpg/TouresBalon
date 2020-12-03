package co.com.puj.aes.reserva.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class Hospedaje implements Serializable {

    private String idProvider;
    private String guestName;
    private String roomNumber;
    private Date checkIn;
    private Date checkOut;
    private String hotel;
    private Long amountH;

    public Long getAmountH() { return amountH; }
}

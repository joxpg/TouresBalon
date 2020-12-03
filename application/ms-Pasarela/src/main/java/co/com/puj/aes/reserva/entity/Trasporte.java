package co.com.puj.aes.reserva.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class Trasporte implements Serializable {
    private String idProvider;
    private String departingCity;
    private String arrivingCity;
    private Date departingDate;
    private int adult;
    private int child;
    private ArrayList<?> passengers;
    private Long amountT;

    public Long getAmountT() { return amountT; }
}

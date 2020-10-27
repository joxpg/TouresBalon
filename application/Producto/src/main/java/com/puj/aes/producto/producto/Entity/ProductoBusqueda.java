package com.puj.aes.producto.producto.Entity;

import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ProductoBusqueda implements Serializable {

    private String departingCity;
    private String arrivingCity;
    private Date departingDate;
    private String cabin;
    private String promotionCode;
    private String city;
    private String country;
    private Date checkIn;
    private Date checkOut;
    private String rooms;
    private String typeofRoom;
    private Date showDate;
    private int quantity;
    private String location;
    private String idBusqueda;
}

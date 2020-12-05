package com.puj.aes.producto.producto.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResultado {
    private String cabin;
    private Double price;
    private String arrivingCity;
    private String meals;
    private Date arrivingDate;
    private Date departingDate;
    private String departingCity;
    private int tripNumber;
    private String flightNumber;
    private String roomNumber;
    private Hotel hotel;
    private String typeofRoom;
    private String row;
    private String seatNumber;
    private String Location;
    private String calificacion;
    private String pesoConvenio;
    private String idBusqueda;
    private String errorCode;

    public ProductoResultado(String idBusqueda){
        this.idBusqueda = idBusqueda;
    }
}


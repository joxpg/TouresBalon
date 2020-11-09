package com.puj.aes.traductor.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.Data;

import java.util.Date;

@Data
public class Peticion {
    public String departingCity;
    public String arrivingCity;
    public Date departingDate;
    public String cabin;
    public String promotionCode;
    public String city;
    public String country;
    public Date checkIn;
    public Date checkOut;
    public String rooms;
    public String typeofRoom;
    public Date showDate;
    public int quantity;
    public String location;
    public String idBusqueda;
    public String productType;
    public Double price;
    public String meals;
    public Date arrivingDate;
    public int tripNumber;
    public String flightNumber;
    public String roomNumber;
    public String row;
    public String seatNumber;
    public String Location;
    public String calificacion;
    public String pesoConvenio;
    public String idBooking;
    public String idCoustumer;
    public String email;
    public String address;
    public String phoneNumber;
    public String contact;
    public boolean active;
    public String idProvider;
    public int adult;
    public int child;
    public String guestName;
    public String hotel;
    public String idShow;
    public String attendeeIdentification;
}

package com.puj.aes.traductor.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.Data;

import java.util.Date;

@Data
public class Peticion {
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
    private String productType;
    private Double price;
    private String meals;
    private Date arrivingDate;
    private int tripNumber;
    private String flightNumber;
    private String roomNumber;
    private String row;
    private String seatNumber;
    private String Location;
    private String calificacion;
    private String pesoConvenio;
    private String idBooking;
    private String idCoustumer;
    private String email;
    private String address;
    private String phoneNumber;
    private String contact;
    private boolean active;
    private String idProvider;
    private int adult;
    private int child;
    private String guestName;
    private String hotel;
    private String idShow;
    private String attendeeIdentification;
}

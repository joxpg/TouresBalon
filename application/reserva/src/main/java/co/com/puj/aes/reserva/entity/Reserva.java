/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.puj.aes.reserva.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Johan Céspedes at PUJ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "reserva")
public class Reserva {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String idBooking;
    @DynamoDBAttribute
    private String idCoustumer;
    @DynamoDBAttribute
    private String email;
    @DynamoDBAttribute
    private String address;
    @DynamoDBAttribute
    private String phoneNumber;
    @DynamoDBAttribute
    private String contact;
    @DynamoDBAttribute
    private boolean active;
    @DynamoDBAttribute
    private String status;
    @DynamoDBAttribute
    private String category;
    @DynamoDBAttribute
    private String country;
    @DynamoDBAttribute
    private Trasporte transportBooking;
    @DynamoDBAttribute
    private Hospedaje hostingBooking;
    @DynamoDBAttribute
    private Espectaculo showBooking;


    public Reserva(String idBooking) {
        this.idBooking = idBooking;
    }

}

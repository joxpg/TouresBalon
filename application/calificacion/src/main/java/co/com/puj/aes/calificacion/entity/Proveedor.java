/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.puj.aes.calificacion.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Johan Céspedes at PUJ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {

    private String idProvider;
    private String productType;
    private String nameProvider;
    private String email;
    private String address;
    private String phoneNumber;
    private String country;
    private String contact;
    private String weight;
    private String rating;
    private boolean active;
    private String description;

    public Proveedor(String idProvider) {
        this.idProvider = idProvider;
    }
}
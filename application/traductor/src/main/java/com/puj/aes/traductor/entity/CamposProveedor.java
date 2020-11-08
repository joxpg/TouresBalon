package com.puj.aes.traductor.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "mapeo")
public class CamposProveedor implements Serializable{
    @DynamoDBHashKey(attributeName = "idProvider")
    private String idProvider;
    //@DynamoDBTypeConverted(converter = CamposToStringTypeConverter.class)
    @DynamoDBAttribute(attributeName="campo")
    private List<Campo> campos;
}

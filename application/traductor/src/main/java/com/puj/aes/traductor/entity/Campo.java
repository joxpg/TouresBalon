package com.puj.aes.traductor.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Campo {
    @DynamoDBAttribute
    private String nombre;
    @DynamoDBAttribute
    private String valorDominio;
    @DynamoDBAttribute
    private String valorProveedor;
}

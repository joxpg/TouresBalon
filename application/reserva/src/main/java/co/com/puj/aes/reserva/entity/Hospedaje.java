package co.com.puj.aes.reserva.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument

public class Hospedaje {

    @DynamoDBAttribute
    private String idProvider;
    @DynamoDBAttribute
    private String amountH;
    @DynamoDBAttribute
    private String guestName;
    @DynamoDBAttribute
    private String roomNumber;
    @DynamoDBAttribute
    private Date checkIn;
    @DynamoDBAttribute
    private Date checkOut;
    @DynamoDBAttribute
    private String hotel;
}

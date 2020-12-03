package co.com.puj.aes.reserva.entity;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument

public class Trasporte {
    @DynamoDBAttribute
    private String idProvider;
    @DynamoDBAttribute
    private Long amountT;
    @DynamoDBAttribute
    private String departingCity;
    @DynamoDBAttribute
    private String arrivingCity;
    @DynamoDBAttribute
    private Date departingDate;
    @DynamoDBAttribute
    private int adult;
    @DynamoDBAttribute
    private int child;
    @DynamoDBAttribute
    @DynamoDBTypeConvertedJson
    private ArrayList<?> passengers;
}

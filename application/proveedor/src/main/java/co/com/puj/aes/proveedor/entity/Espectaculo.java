package co.com.puj.aes.proveedor.entity;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Espectaculo {
    private String idProvider;
    private String idShow;
    private Date showDate;
    private String attendeeIdentification;
}

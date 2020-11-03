package co.com.puj.aes.proveedor.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Trasporte {

    private String idProvider;
    private String departingCity;
    private String arrivingCity;
    private Date departingDate;
    private int adult;
    private int child;
    private ArrayList<?> passengers;
}

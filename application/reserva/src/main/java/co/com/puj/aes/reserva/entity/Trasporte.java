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
    private String idProveedor;
    @DynamoDBAttribute
    private String origen;
    @DynamoDBAttribute
    private String destino;
    @DynamoDBAttribute
    private Date fechaSalida;
    @DynamoDBAttribute
    private Date fechaRegreso;
    @DynamoDBAttribute
    private int adultos;
    @DynamoDBAttribute
    private int niños;
    @DynamoDBAttribute
    @DynamoDBTypeConvertedJson
    private ArrayList<?> pasajeros;
}

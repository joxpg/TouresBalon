package co.com.puj.aes.msBusqueda.Entity;

import co.com.puj.aes.reserva.entity.Espectaculo;
import co.com.puj.aes.reserva.entity.Hospedaje;
import co.com.puj.aes.reserva.entity.Trasporte;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "reserva")
public class BusquedaReserva implements Serializable {
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
    private String country;
    @DynamoDBAttribute
    private Trasporte transportBooking;
    @DynamoDBAttribute
    private Hospedaje hostingBooking;
    @DynamoDBAttribute
    private Espectaculo showBooking;


    public BusquedaReserva(String idBooking) {
        this.idBooking = idBooking;
    }

}
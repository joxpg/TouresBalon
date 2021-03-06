package co.com.puj.aes.reserva.entity;

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
@DynamoDBTable(tableName = "aprobacion")

public class Aprobacion {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String idApproval;
    @DynamoDBAttribute
    private String idBooking;
    @DynamoDBAttribute
    private String status;
    @DynamoDBAttribute
    private boolean active;
    public Aprobacion(String idBooking) {
        this.idBooking = idBooking;
    }
}

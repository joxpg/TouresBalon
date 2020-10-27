package co.com.puj.aes.reserva.repository;
import co.com.puj.aes.reserva.entity.Reserva;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservaRepository {

@Autowired
    private DynamoDBMapper dynamoDbMapper;

    public Reserva save(Reserva reserva) {
        dynamoDbMapper.save(reserva);
        return reserva;
    }

    public Reserva getProveedorById (String idReserva){
        return dynamoDbMapper.load(Reserva.class, idReserva);
    }

    public  Reserva update (String idReserva, Reserva reserva){
        dynamoDbMapper.save(reserva,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idReserva",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idReserva)
                                )));
        return dynamoDbMapper.load(Reserva.class, idReserva);

    }

    public  String delete (String idReserva){
        Reserva reserva = getProveedorById(idReserva);
        reserva.setEstado(false);
        dynamoDbMapper.save(reserva,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idReserva",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idReserva)
                                )));
        return idReserva +"  Reserva Eliminado";
    }
}

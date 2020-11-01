package co.com.puj.aes.reserva.repository;
import co.com.puj.aes.reserva.entity.Reserva;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservaRepository {

@Autowired
    private DynamoDBMapper dynamoDbMapper;

    public Reserva save(Reserva reserva) {
        dynamoDbMapper.save(reserva);
        return reserva;
    }

    public Reserva getProveedorById (String idBooking) throws Exception{
        return dynamoDbMapper.load(Reserva.class, idBooking);
    }

    public List<Reserva> getlist ()throws Exception{
        List<Reserva> scanResult = dynamoDbMapper.scan(Reserva.class, new DynamoDBScanExpression());
        ((PaginatedScanList<Reserva>) scanResult).loadAllResults();
        return scanResult;
    }

    public  Reserva update (String idBooking, Reserva reserva){
        dynamoDbMapper.save(reserva,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idBooking",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idBooking)
                                )));
        return dynamoDbMapper.load(Reserva.class, idBooking);

    }

    public  String delete (String idBooking) throws Exception {
        Reserva reserva = getProveedorById(idBooking);
        reserva.setActive(false);
        dynamoDbMapper.save(reserva,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idBooking",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idBooking)
                                )));
        return idBooking +"  Reserva Eliminado";
    }
}

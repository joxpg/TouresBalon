package co.com.puj.aes.reserva.service;

import co.com.puj.aes.msBusqueda.Entity.BusquedaReserva;
import co.com.puj.aes.reserva.entity.Aprobacion;
import co.com.puj.aes.reserva.exceptions.ResourceNotFoundException;
import co.com.puj.aes.reserva.repository.AprobacionRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AprobacionService implements ServiceInterface <Aprobacion, String>{

@Autowired
    private DynamoDBMapper dynamoDbMapper;
@Autowired
    private AprobacionRepository aprobacionRepository;



    public Aprobacion save(Aprobacion aprobacion) {
        aprobacion.setActive(true);
        dynamoDbMapper.save(aprobacion);
        return aprobacion;
    }

    public List<Aprobacion> getlist ()throws Exception{
        List<Aprobacion> scanResult = dynamoDbMapper.scan(Aprobacion.class, new DynamoDBScanExpression());
        ((PaginatedScanList<Aprobacion>) scanResult).loadAllResults();
        return scanResult;
    }

    public  BusquedaReserva updatePagoReserva (String idBooking, BusquedaReserva reserva){
        dynamoDbMapper.save(reserva,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idBooking",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idBooking)
                                )));
        return dynamoDbMapper.load(BusquedaReserva.class, idBooking);

    }
    public Aprobacion getAprobacionById (String idBooking) throws Exception{
        return dynamoDbMapper.load(Aprobacion.class, idBooking);
    }

    public Aprobacion findByIdBooking(String idBooking) throws Exception {
        if(idBooking == null ){
            throw new ResourceNotFoundException("El Descuento con id "+idBooking+" no existe.");
        }
        return aprobacionRepository.findByIdBooking(idBooking);
    }



    public  Aprobacion update (String idBooking, Aprobacion aprobacion){
        dynamoDbMapper.save(aprobacion,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idBooking",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idBooking)
                                )));
        return dynamoDbMapper.load(Aprobacion.class, idBooking);

    }

    public  String deleteAprobacion (String idBooking) throws Exception {
        Aprobacion aprobacion = getAprobacionById(idBooking);
        aprobacion.setActive(false);
        dynamoDbMapper.save(aprobacion,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idBooking",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idBooking)
                                )));
        return idBooking +"Reserva Eliminado";
    }

    @Override
    public Aprobacion findById(String key) throws Exception {
        return null;
    }

    @Override
    public List<Aprobacion> findAll() throws Exception {
        return null;
    }

    @Override
    public Aprobacion create(Aprobacion entity) throws Exception {
        return null;
    }

    @Override
    public Aprobacion update(Aprobacion entity) throws Exception {
        return null;
    }

    @Override
    public void delete(String key) throws Exception {

    }
}

package co.com.puj.aes.reserva.service;


import co.com.puj.aes.msBusqueda.Entity.BusquedaReserva;
import co.com.puj.aes.reserva.entity.Aprobacion;
import co.com.puj.aes.reserva.entity.Reserva;
import co.com.puj.aes.reserva.repository.ReservaRepository;
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
public  class ReservaService  implements ServiceInterface <Reserva, String>  {

    @Autowired
    private DynamoDBMapper dynamoDbMapper;

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva save(Reserva reserva) {
        dynamoDbMapper.save(reserva);
        return reserva;
    }
    public Aprobacion saveAprobacion(Aprobacion aprobacion) {
        dynamoDbMapper.save(aprobacion);
        return aprobacion;
    }

    public BusquedaReserva savePagoReserva(BusquedaReserva reserva) {
        dynamoDbMapper.save(reserva);
        return reserva;
    }

    public BusquedaReserva getBookingById (String idBooking) throws Exception{
        return dynamoDbMapper.load(BusquedaReserva.class, idBooking);
    }
    public Reserva getReservaById (String idBooking) throws Exception{
        return dynamoDbMapper.load(Reserva.class, idBooking);
    }

    public List<Reserva> getlist1 ()throws Exception{
        System.out.println(" entrando en el List Dynamo" );
        List<Reserva> scanResult = dynamoDbMapper.scan(Reserva.class, new DynamoDBScanExpression());
        ((PaginatedScanList<Reserva>) scanResult).loadAllResults();
        return scanResult;
    }
    public List<Reserva> getlist ()throws Exception{
        System.out.println(" entrando en el List Spring" );
        return (List <Reserva>) reservaRepository.findAll();
    }

    public  Reserva updateBooking (String idBooking, BusquedaReserva reserva) throws Exception {
        Reserva reserva1 = getReservaById(reserva.getIdBooking());
        if (reserva.isActive()) {
            reserva1.setActive(reserva.isActive());
        }
        if (reserva.getAddress() != null) {
            reserva1.setAddress(reserva.getAddress());
        }
        if (reserva.getStatus() != null) {
            reserva1.setStatus(reserva.getStatus());
        }
        if (reserva.getCategory() != null) {
            reserva1.setCategory(reserva.getCategory());
        }
        if (reserva.getContact() != null) {
            reserva1.setContact(reserva.getContact());
        }
        if (reserva.getCountry() != null) {
            reserva1.setCountry(reserva.getCountry());
        }
        if (reserva.getIdCoustumer() != null) {
            reserva1.setIdCoustumer(reserva.getIdCoustumer());
        }
        if (reserva.getPhoneNumber() != null) {
            reserva1.setPhoneNumber(reserva.getPhoneNumber());
        }
        if (reserva.getEmail() != null) {
            reserva1.setEmail(reserva.getEmail());
        }
        dynamoDbMapper.save(reserva1,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idBooking",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idBooking)
                                )));
        return dynamoDbMapper.load(Reserva.class, idBooking);

    }
        /*public  Reserva updateApproval (String idBooking, BusquedaReserva reserva){
            dynamoDbMapper.save(reserva,
                    new DynamoDBSaveExpression()
                            .withExpectedEntry("idBooking",
                                    new ExpectedAttributeValue(
                                            new AttributeValue().withS(idBooking)
                                    )));
            return dynamoDbMapper.load(Reserva.class, idBooking);

        }*/

    public  String deleteReserva (String idBooking) throws Exception {
        Reserva reserva = getReservaById(idBooking);
        reserva.setActive(false);
        dynamoDbMapper.save(reserva,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idBooking",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idBooking)
                                )));
        return idBooking +"  Reserva Eliminado";
    }


    @Override
    public Reserva findById(String key) throws Exception {
        return null;
    }

    @Override
    public List<Reserva> findAll() throws Exception {
        return null;
    }

    @Override
    public Reserva create(Reserva entity) throws Exception {
        return null;
    }

    @Override
    public Reserva update(Reserva reserva) throws Exception {
        Reserva reserva1 = getReservaById(reserva.getIdBooking());
        if (reserva.isActive()) {
            reserva1.setActive(reserva.isActive());
        }
        if (reserva.getAddress() != null) {
            reserva1.setAddress(reserva.getAddress());
        }
        if (reserva.getStatus() != null) {
            reserva1.setStatus(reserva.getStatus());
        }
        if (reserva.getCategory() != null) {
            reserva1.setCategory(reserva.getCategory());
        }
        if (reserva.getContact() != null) {
            reserva1.setContact(reserva.getContact());
        }
        if (reserva.getCountry() != null) {
            reserva1.setCountry(reserva.getCountry());
        }
        if (reserva.getIdCoustumer() != null) {
            reserva1.setIdCoustumer(reserva.getIdCoustumer());
        }
        if (reserva.getPhoneNumber() != null) {
            reserva1.setPhoneNumber(reserva.getPhoneNumber());
        }
        if (reserva.getEmail() != null) {
            reserva1.setEmail(reserva.getEmail());
        }
        return reservaRepository.save(reserva);
    }

    @Override
    public void delete(String key) throws Exception {

    }

}

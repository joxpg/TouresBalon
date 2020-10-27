package co.com.puj.aes.pagos.service;

import co.com.puj.aes.pagos.entity.Pagos;
import co.com.puj.aes.pagos.repository.PagosRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

import java.util.List;


@Service
public class PagosService implements ServiceInterface <Pagos, String>  {

@Autowired
private DynamoDBMapper dynamoDbMapper;

//@Autowired
private PagosRepository pagosRepository;

public Pagos save(Pagos pagos) throws Exception{
        dynamoDbMapper.save(pagos);
        return pagos;
        }

public Pagos getPagoById (String idProveedor) throws Exception{
        return dynamoDbMapper.load(Pagos.class, idProveedor);
        }

public  Pagos update (String idPagos, Pagos pagos) throws Exception{
        dynamoDbMapper.save(pagos,
        new DynamoDBSaveExpression()
        .withExpectedEntry("idPagos",
        new ExpectedAttributeValue(
        new AttributeValue().withS(idPagos)
        )));
        return dynamoDbMapper.load(Pagos.class, idPagos);

        }

    public String deletePagos (String idPagos) throws Exception{
        Pagos pagos = getPagoById(idPagos);
        pagos.setEstado(false);
        dynamoDbMapper.save(pagos,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idPagos",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idPagos)
                                )));
        return idPagos +"  Pago Eliminado";
    }

    @Override
    public Pagos findById(String key) throws Exception {
        return null;
    }

    @Override
    public List<Pagos> findAll() throws Exception {
        return (List <Pagos>) pagosRepository;
    }

    @Override
    public Pagos create(Pagos entity) throws Exception {
        return null;
    }

    @Override
    public Pagos update(Pagos entity) throws Exception {
        return null;
    }

    @Override
    public void delete(String key) throws Exception {

    }


}



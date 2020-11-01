package co.com.puj.aes.proveedor.service;

import co.com.puj.aes.proveedor.entity.Proveedor;
import co.com.puj.aes.proveedor.repository.ProveedorRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

import java.util.List;


@Service
public class  ProveedorService implements ServiceInterface <Proveedor, String>  {

@Autowired
private DynamoDBMapper dynamoDbMapper;

//@Autowired
private ProveedorRepository proveedorRepository;

public Proveedor save(Proveedor proveedor) throws Exception{
        dynamoDbMapper.save(proveedor);
        return proveedor;
        }

public Proveedor getProveedorById (String idProvider) throws Exception{
        return dynamoDbMapper.load(Proveedor.class, idProvider);
        }
    public List<Proveedor> getlist ()throws Exception{
        List<Proveedor> scanResult = dynamoDbMapper.scan(Proveedor.class, new DynamoDBScanExpression());
        ((PaginatedScanList<Proveedor>) scanResult).loadAllResults();
        System.out.println("getList size en el servicio " + scanResult.size());

        return scanResult;
    }

public  Proveedor update (String idProvider, Proveedor proveedor) throws Exception{
        dynamoDbMapper.save(proveedor,
        new DynamoDBSaveExpression()
        .withExpectedEntry("idProvider",
        new ExpectedAttributeValue(
        new AttributeValue().withS(idProvider)
        )));
        return dynamoDbMapper.load(Proveedor.class, idProvider);

        }

    /*public String deleteProveedor (String idProvider) throws Exception{
        Proveedor proveedor = getProveedorById(idProvider);
        proveedor.setActive(false);
        dynamoDbMapper.save(proveedor,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idProvider",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idProvider)
                                )));
        return idProvider +"  Proveedor Eliminado";
    }*/
    public String deleteProveedor (String idProvider){
        Proveedor proveedor = dynamoDbMapper.load(Proveedor.class, idProvider);
        dynamoDbMapper.delete(proveedor);
        return "Proveedor Eliminado!";

    }

    @Override
    public Proveedor findById(String key) throws Exception {
        return null;
    }

    @Override
    public List<Proveedor> findAll() throws Exception {
        return (List <Proveedor>) proveedorRepository;
    }

    @Override
    public Proveedor create(Proveedor entity) throws Exception {
        return null;
    }

    @Override
    public Proveedor update(Proveedor entity) throws Exception {
        return null;
    }

    @Override
    public void delete(String key) throws Exception {

    }


}



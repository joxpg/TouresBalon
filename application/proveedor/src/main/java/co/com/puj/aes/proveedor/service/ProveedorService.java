package co.com.puj.aes.proveedor.service;

import co.com.puj.aes.proveedor.entity.Proveedor;
import co.com.puj.aes.proveedor.repository.ProveedorRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
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

public Proveedor getProveedorById (String idProveedor) throws Exception{
        return dynamoDbMapper.load(Proveedor.class, idProveedor);
        }

public  Proveedor update (String idProveedor, Proveedor proveedor) throws Exception{
        dynamoDbMapper.save(proveedor,
        new DynamoDBSaveExpression()
        .withExpectedEntry("idProveedor",
        new ExpectedAttributeValue(
        new AttributeValue().withS(idProveedor)
        )));
        return dynamoDbMapper.load(Proveedor.class, idProveedor);

        }

    public String deleteProveedor (String idProveedor) throws Exception{
        Proveedor proveedor = getProveedorById(idProveedor);
        proveedor.setEstado(false);
        dynamoDbMapper.save(proveedor,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("idProveedor",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(idProveedor)
                                )));
        return idProveedor +"  Proveedor Eliminado";
    }
    /*public String delete (String idProveedor){
        Proveedor proveedor = dynamoDbMapper.load(Proveedor.class, idProveedor);
        dynamoDbMapper.delete(proveedor);
        return "Proveedor Eliminado!";

    }*/

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



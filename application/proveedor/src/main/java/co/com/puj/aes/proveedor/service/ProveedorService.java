package co.com.puj.aes.proveedor.service;

import co.com.puj.aes.proveedor.entity.Proveedor;
import co.com.puj.aes.proveedor.repository.ProveedorRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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

    public List<Proveedor> getlistByProductType (String type)throws Exception{

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":proveedor", new AttributeValue().withS(type));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("productType = :proveedor")
                .withExpressionAttributeValues(eav);
        List<Proveedor> list = dynamoDbMapper.scan(Proveedor.class, scanExpression);
        return list;

    }
    public  Proveedor update (String idProvider, Proveedor proveedor) throws Exception{
            Proveedor proveedor1 = getProveedorById(proveedor.getIdProvider());

         if (proveedor.getNameProvider() != null) {
                proveedor1.setNameProvider(proveedor.getNameProvider());
        }
        if (proveedor.getRating() != null) {
            proveedor1.setRating(proveedor.getRating());
        }
        if (proveedor.getAddress() != null) {
            proveedor1.setAddress(proveedor.getAddress());
        }
        if (proveedor.getCategory() != null) {
            proveedor1.setCategory(proveedor.getCategory());
        }
        if (proveedor.getContact() != null) {
            proveedor1.setContact(proveedor.getContact());
        }
        if (proveedor.getCountry() != null) {
            proveedor1.setCountry(proveedor.getCountry());
        }
        if (proveedor.getDescription() != null) {
            proveedor1.setDescription(proveedor.getDescription());
        }
        if (proveedor.getPhoneNumber() != null) {
            proveedor1.setPhoneNumber(proveedor.getPhoneNumber());
        }
        if (proveedor.getProductType() != null) {
            proveedor1.setProductType(proveedor.getProductType());
        }
        if (proveedor.getEmail() != null) {
            proveedor1.setEmail(proveedor.getEmail());
        }
        if (proveedor.getWeight() != null) {
            proveedor1.setWeight(proveedor.getWeight());
        }
        dynamoDbMapper.save(proveedor1,
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



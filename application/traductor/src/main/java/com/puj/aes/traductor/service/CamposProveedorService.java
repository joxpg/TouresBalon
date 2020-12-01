package com.puj.aes.traductor.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.puj.aes.traductor.entity.Campo;
import com.puj.aes.traductor.entity.CamposProveedor;
import com.puj.aes.traductor.entity.Peticion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.util.List;

@Service
public class CamposProveedorService implements ICamposProveedor<CamposProveedor, String> {
    @Autowired
    private DynamoDBMapper dynamoDbMapper;

    public CamposProveedor save(CamposProveedor camposProveedor) throws Exception{
        dynamoDbMapper.save(camposProveedor);
        return camposProveedor;
    }

    public CamposProveedor getCamposProveedorById (String idProvider) throws Exception{
        return dynamoDbMapper.load(CamposProveedor.class, idProvider);
    }

    public Peticion traducirPeticion(Peticion peticion, String idProvider) throws Exception {
        Class<?> clase = peticion.getClass();
        PropertyDescriptor[] objDescriptors = PropertyUtils.getPropertyDescriptors(peticion);
        for (PropertyDescriptor objDescriptor : objDescriptors) {
            try {
                String propertyName = objDescriptor.getName();
                Object propValue = PropertyUtils.getProperty(peticion, propertyName);
                if(propValue != null){
                    String vproveedor = obtenerValorProveedor(idProvider,propertyName,propValue.toString());
                    if (vproveedor != null) {
                        clase.getDeclaredField(propertyName).set(peticion, vproveedor);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return peticion;
    }

    private String obtenerValorProveedor(String idProvider, String propertyName, String value) throws Exception {
        CamposProveedor camposProveedor = getCamposProveedorById(idProvider);
        String vproveedor = null;
        if (camposProveedor.getCampos().length > 0) {
            for (Campo campo : camposProveedor.getCampos()) {
                String nombreCampo = campo.getNombre();
                String valorDominio = campo.getValorDominio();
                String valorProveedor = campo.getValorProveedor();
                if (nombreCampo.equals(propertyName) && valorDominio.equals(value)){
                   vproveedor = valorProveedor;
                   break;
                }
            }
        }
        return vproveedor;
    }
    //TODO traducirRespuesta
    public Peticion traducirRespuesta(Peticion respuesta, String idProvider) throws Exception {
        Class<?> clase = respuesta.getClass();
        PropertyDescriptor[] objDescriptors = PropertyUtils.getPropertyDescriptors(respuesta);
        for (PropertyDescriptor objDescriptor : objDescriptors) {
            try {
                String propertyName = objDescriptor.getName();
                Object propValue = PropertyUtils.getProperty(respuesta, propertyName);
                if(propValue != null){
                    String vdominio = obtenerValorDominio(idProvider,propertyName,propValue.toString());
                    if (vdominio != null) {
                        clase.getDeclaredField(propertyName).set(respuesta, vdominio);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return respuesta;
    }
    //TODO obtenerValorDominio
    private String obtenerValorDominio(String idProvider, String propertyName, String value) throws Exception {
        CamposProveedor camposProveedor = getCamposProveedorById(idProvider);
        String vdominio = null;
        if (camposProveedor.getCampos().length > 0) {
            for (Campo campo : camposProveedor.getCampos()) {
                String nombreCampo = campo.getNombre();
                String valorDominio = campo.getValorDominio();
                String valorProveedor = campo.getValorProveedor();
                if (nombreCampo.equals(propertyName) && valorProveedor.equals(value)){
                    vdominio = valorDominio;
                    break;
                }
            }
        }
        return vdominio;
    }



    @Override
    public CamposProveedor findById(String key) throws Exception {
        return null;
    }

    @Override
    public List<CamposProveedor> findAll() throws Exception {
        return null;
    }

    @Override
    public CamposProveedor create(CamposProveedor entity) throws Exception {
        return null;
    }

    @Override
    public CamposProveedor update(CamposProveedor entity) throws Exception {
        return null;
    }

    @Override
    public void delete(String key) throws Exception {

    }
}

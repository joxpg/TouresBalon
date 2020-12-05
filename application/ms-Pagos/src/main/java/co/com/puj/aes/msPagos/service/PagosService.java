package co.com.puj.aes.msPagos.service;


import co.com.puj.aes.msPagos.entity.Pagos;
import co.com.puj.aes.msPagos.exeptions.ResourceNotFoundException;
import co.com.puj.aes.msPagos.repository.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.sql.SQLOutput;
import java.util.*;

@Service
public class PagosService implements ServiceInterface <Pagos, String>{
    //@Autowired
    private PagosRepository pagosRepository;

    @Override
    public Pagos findById(String key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("El pago con id " + key + " no existe.");
        }
        return pagosRepository.findById(key).get();
    }
    @Override
    public Pagos findByIdBooking(String key) throws Exception {
        if (key == null || !existeByIdBooking(key)) {
            throw new ResourceNotFoundException("El pago con id " + key + " no existe.");
        }
        return pagosRepository.findByIdBooking(key);
    }

    @Override
    public List<Pagos> findAll() throws Exception {
        return (List <Pagos>) pagosRepository.findAll();
    }

    @Override
    public Pagos create(Pagos entity) throws Exception {
        System.out.println("error: "+entity.getIdPayment());
        return pagosRepository.save(entity);
    }

    @Override
    public Pagos update(Pagos entity) throws Exception {
        //Pagos pagos = pagosRepository.findById(entity.getIdPayment()).get();
        Pagos pagos = pagosRepository.findByIdBooking(entity.getIdBooking());

        if (entity.getIdBooking() != null) {
            pagos.setIdBooking(entity.getIdBooking());
        }
        if (entity.getAmountT() != null) {
            pagos.setAmountT(entity.getAmountT());
        }
        if (entity.getAmountH() != null) {
            pagos.setAmountH(entity.getAmountH());
        }
        if (entity.getAmountS() != null) {
            pagos.setAmountS(entity.getAmountS());
        }
        if (entity.getActive() != null) {
            pagos.setActive(entity.getActive());
        }
        return pagosRepository.save(pagos);
    }

    @Override
    public void delete(String key) throws Exception {

    }

    public Pagos cancelacion(String key) throws Exception {
        Pagos pagos = pagosRepository.findByIdBooking(key);
        pagos.setActive(false);
        return pagosRepository.save(pagos);
    }
    public Boolean existeById (String key) throws Exception {
        if(key==null){
            throw new ResourceNotFoundException("El ID a validar, no puede estar vacio.");
        }
        if (pagosRepository.findById(key) == null) {
            return false;
        }
        return pagosRepository.existsById(key);
    }

    public Boolean existeByIdBooking (String key) throws Exception {
        if(key==null){
            throw new ResourceNotFoundException("El ID a validar, no puede estar vacio.");
        }
        if (pagosRepository.findByIdBooking(key) == null) {
            return false;
        }
        return pagosRepository.existsByIdBooking(key);
    }

}

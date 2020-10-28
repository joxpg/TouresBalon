package co.com.puj.aes.msPagos.service;


import co.com.puj.aes.msPagos.entity.Pagos;
import co.com.puj.aes.msPagos.exeptions.ResourceNotFoundException;
import co.com.puj.aes.msPagos.repository.PagosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PagosService implements ServiceInterface  <Pagos, Short>{
    @Autowired
    private PagosRepository pagosRepository;

    @Override
    public Pagos findById(Short key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("El pago con id " + key + " no existe.");
        }
        return pagosRepository.findById(key).get();
    }

    @Override
    public List<Pagos> findAll() throws Exception {
        return (List <Pagos>) pagosRepository.findAll();
    }
    public List <Map<String, String>> findAllByPagos() throws Exception {
        return pagosRepository.findAllByPagos();
    }

    @Override
    public Pagos create(Pagos entity) throws Exception {
        entity.setEstadoPagos(true);
        return pagosRepository.save(entity);
    }

    @Override
    public Pagos update(Pagos entity) throws Exception {
        Pagos pagos = pagosRepository.findById(entity.getIdPagos()).get();

        if (entity.getIdReserva() != null) {
            pagos.setIdReserva(entity.getIdReserva());
        }

        return pagosRepository.save(pagos);
    }

    @Override
    public void delete(Short key) throws Exception {
        
    }

    public Pagos deletePagos(Short key) throws Exception {
        Pagos pagos = pagosRepository.findById(key).get();
        pagos.setEstadoPagos(false);
        return pagosRepository.save(pagos);
    }

    public Boolean existeById (Short key) throws Exception {
        if(key==null){
            throw new ResourceNotFoundException("El ID a validar, no puede estar vacio.");
        }
        if (pagosRepository.findById(key) == null) {
            return false;
        }
        return pagosRepository.existsById(key);
    }

    public Pagos findId(Short key) throws Exception {
        if (key == null || !existeById(key)) {
            throw new ResourceNotFoundException("El pagos con id " + key + " no existe.");
        }
        return pagosRepository.findById(key).get();
    }

    /**
     * Metodo para activar pagos
     * @param key id de pagos
     * @return
     * @throws Exception
     */
    @Deprecated
    public Pagos activar(Short key) throws Exception {
        Pagos pagos = pagosRepository.findById(key).get();
        pagos.setEstadoPagos(true);
        return pagosRepository.save(pagos);
    }

}

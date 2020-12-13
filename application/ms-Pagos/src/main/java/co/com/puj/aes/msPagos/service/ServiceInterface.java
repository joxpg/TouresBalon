package co.com.puj.aes.msPagos.service;

import co.com.puj.aes.msPagos.entity.Pagos;

import java.util.List;

public interface ServiceInterface <E, PK>  {
    Pagos findById(String key) throws Exception;

    Pagos findByIdBooking(String key) throws Exception;

    List<Pagos> findAll() throws Exception;

    Pagos create(Pagos entity) throws Exception;

    Pagos update(Pagos entity) throws Exception;

    void delete(String key) throws Exception;
/*
    E findById(PK key) throws Exception;

    List<E> findAll() throws Exception;

    E create(E entity) throws Exception;
    E update(E entity) throws Exception;

    void delete(PK key) throws Exception;

*/

}

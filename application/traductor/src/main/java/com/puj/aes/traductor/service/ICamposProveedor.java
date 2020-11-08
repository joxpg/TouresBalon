package com.puj.aes.traductor.service;

import java.util.List;

public interface ICamposProveedor<E, PK> {
    E findById(PK key) throws Exception;

    List<E> findAll() throws Exception;

    E create(E entity) throws Exception;
    E update(E entity) throws Exception;

    void delete(PK key) throws Exception;
}

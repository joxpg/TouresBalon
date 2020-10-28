package com.puj.aes.producto.producto.Interface;

import com.puj.aes.producto.producto.Entity.*;


public interface IProductoService {
    public ProductoResultado enviarRespuesta(ProductoResultado respuesta);
    public ProductoResultado buscarProducto(String busqueda);
}

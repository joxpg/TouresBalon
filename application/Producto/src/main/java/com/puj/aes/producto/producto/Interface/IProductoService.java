package com.puj.aes.producto.producto.Interface;

import com.puj.aes.producto.producto.Entity.*;


public interface IProductoService {
    public void enviarRespuesta(Proveedor proveedor, ProductoResultado respuesta);
    public void buscarProducto(String busqueda);
}

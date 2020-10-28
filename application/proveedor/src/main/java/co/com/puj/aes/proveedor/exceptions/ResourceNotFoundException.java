package co.com.puj.aes.proveedor.exceptions;

/**
 * @author Johan Céspedes ortega At PUJ
 * @date 29/08/2020
 * Modela los mensajes de error que produce la aplicacion cuando el dato solicitado no existeKey.
 */
public class ResourceNotFoundException extends RuntimeException {
    public static final String DESCRIPCION = "Recurso sin resultados";

    public ResourceNotFoundException(){
        super(DESCRIPCION);
    }

    public ResourceNotFoundException(String detalleError){
        super(DESCRIPCION+" : "+detalleError);
    }


}

package co.com.puj.aes.calificacion.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public static final String DESCRIPCION = "Recurso sin resultados";

    public ResourceNotFoundException(){
        super(DESCRIPCION);
    }

    public ResourceNotFoundException(String detalleError){
        super(DESCRIPCION+" : "+detalleError);
    }
}

package co.com.puj.aes.msPagos.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends Exception {
    public static final String DESCRIPCION = "Error interno del servidor";

    public ServerErrorException(){
        super(DESCRIPCION);
    }

    public ServerErrorException(String detalleError){
        super(DESCRIPCION+" : "+detalleError);
    }


}

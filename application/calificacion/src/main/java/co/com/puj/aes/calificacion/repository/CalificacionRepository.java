package co.com.puj.aes.calificacion.repository;
import co.com.puj.aes.calificacion.entity.Calificacion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalificacionRepository extends CrudRepository <Calificacion, Short> {

    Calificacion findByIdProveedor(String idProveedor);

}


package co.com.puj.aes.calificacion.repository;
import co.com.puj.aes.calificacion.entity.Calificacion;
import co.com.puj.aes.calificacion.entity.HistorialCalificacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface HistorialCalificacionRepository extends CrudRepository<HistorialCalificacion, Short> {

    @Query(value="SELECT ROUND (avg (c.\"CALIFICACION\") )AS total\n" +
            "    FROM \"calificacionSch\".\"HISTORIAL_CALIFICACION\" as c\n" +
            "    WHERE c.\"ID_PROVEEDOR\"=:idProveedor",nativeQuery = true)
    String avgCalificacionProveedor(@Param("idProveedor") String idProveedor);
}

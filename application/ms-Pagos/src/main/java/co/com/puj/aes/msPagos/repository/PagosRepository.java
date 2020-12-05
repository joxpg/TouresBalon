package co.com.puj.aes.msPagos.repository;

import co.com.puj.aes.msPagos.entity.Pagos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PagosRepository extends CrudRepository<Pagos, String>{

    Pagos findByIdBooking(String idBooking);

    Boolean existsByIdBooking(String idBooking);
}

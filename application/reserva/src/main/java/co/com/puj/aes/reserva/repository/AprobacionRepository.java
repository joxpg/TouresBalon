package co.com.puj.aes.reserva.repository;

import co.com.puj.aes.reserva.entity.Aprobacion;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface AprobacionRepository extends CrudRepository<Aprobacion, String> {
    Aprobacion findByIdBooking(String idBooking);
}

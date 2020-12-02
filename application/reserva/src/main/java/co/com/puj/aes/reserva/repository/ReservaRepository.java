package co.com.puj.aes.reserva.repository;

import co.com.puj.aes.reserva.entity.Reserva;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface ReservaRepository extends CrudRepository<Reserva, String> {

}
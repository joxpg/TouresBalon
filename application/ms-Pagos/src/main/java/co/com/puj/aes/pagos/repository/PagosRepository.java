package co.com.puj.aes.pagos.repository;
import co.com.puj.aes.pagos.entity.Pagos;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface PagosRepository extends CrudRepository <Pagos, String> {


}

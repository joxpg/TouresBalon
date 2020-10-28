package co.com.puj.aes.proveedor.repository;
import co.com.puj.aes.proveedor.entity.Proveedor;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ProveedorRepository extends CrudRepository <Proveedor, String> {


}

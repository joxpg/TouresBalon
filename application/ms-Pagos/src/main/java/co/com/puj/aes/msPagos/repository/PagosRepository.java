package co.com.puj.aes.msPagos.repository;

import co.com.puj.aes.msPagos.entity.Pagos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PagosRepository extends CrudRepository<Pagos, Short>{
    Pagos findEpsByIdEps(Short idEps);
    Pagos findByIdEpsAndEstadoEpsTrue(Short idEps);


    @Query(value=" SELECT COUNT(\"simedSch\".\"USUARIO\".\"ID_EPS\") AS TOTAL,\n" +
            "\"simedSch\".\"EPS\".\"NOMBRE_EPS\" AS NOMBRE_EPS\n" +
            "FROM \"simedSch\".\"USUARIO\"\n" +
            "JOIN \"simedSch\".\"EPS\" ON \"simedSch\".\"USUARIO\".\"ID_EPS\" = \"simedSch\".\"EPS\".\"ID_EPS\"\n" +
            "GROUP BY \"simedSch\".\"EPS\".\"NOMBRE_EPS\"",nativeQuery = true)


    List<Map<String, String>> findAllByPagos();
}

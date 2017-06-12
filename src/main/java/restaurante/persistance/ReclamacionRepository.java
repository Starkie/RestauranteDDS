package restaurante.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restaurante.domain.Reclamacion;

@Repository
public interface ReclamacionRepository extends CrudRepository<Reclamacion,Long>{

}

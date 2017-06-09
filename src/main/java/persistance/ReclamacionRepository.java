package persistance;

import model.Reclamacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamacionRepository extends CrudRepository<Reclamacion,Long>{

}

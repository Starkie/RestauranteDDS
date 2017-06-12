package persistance;

import model.Plato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends CrudRepository<Plato,Long>{

}

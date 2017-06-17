package restaurante.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restaurante.domain.Plato;

@Repository
public interface PlatoRepository extends CrudRepository<Plato,Long>{

}

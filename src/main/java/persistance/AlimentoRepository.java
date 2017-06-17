package persistance;

import domain.Alimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoRepository extends CrudRepository<Alimento, Long> {
    Alimento findByNombre(String nombre);
}

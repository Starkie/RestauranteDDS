package persistance;

import model.PedidoRestaurante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRestauranteRepository extends CrudRepository<PedidoRestaurante,Long> {
}

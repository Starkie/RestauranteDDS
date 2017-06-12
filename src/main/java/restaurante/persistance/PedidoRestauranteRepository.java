package restaurante.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restaurante.domain.PedidoRestaurante;

@Repository
public interface PedidoRestauranteRepository extends CrudRepository<PedidoRestaurante,Long> {
}

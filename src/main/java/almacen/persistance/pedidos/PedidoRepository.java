package almacen.persistance.pedidos;

import almacen.model.pedidos.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository  extends CrudRepository<Pedido, Long>{

}

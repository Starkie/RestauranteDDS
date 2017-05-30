package almacen.persistencia.pedidos;

import almacen.modelo.pedidos.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository  extends CrudRepository<Pedido, Long>{

}

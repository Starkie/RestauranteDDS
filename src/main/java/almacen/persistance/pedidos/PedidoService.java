package almacen.persistance.pedidos;

import almacen.pedidos.domain.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistance.CrudService;

@Service("pedidoService")
public class PedidoService implements CrudService<Pedido,Long> {
    @Autowired
    private PedidoRepository repository;

    @Override
    public void add(Pedido pedido) {
        repository.save(pedido);
    }

    @Override
    public void update(Pedido pedido) {
        repository.save(pedido);
    }

    @Override
    public void remove(Pedido pedido) {
        repository.delete(pedido);
    }

    @Override
    public Pedido findById(Long id) {
        return  repository.findOne(id);
    }

    @Override
    public Iterable<Pedido> findAll() {
        return repository.findAll();
    }
}

package persistance;
import model.PedidoRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pedidoRestauranteService")
public class PedidoRestauranteService implements CrudService<PedidoRestaurante,Long>{
    @Autowired
    private PedidoRestauranteRepository repository;

    @Override
    public void add(PedidoRestaurante entidad) {
        repository.save(entidad);
    }

    @Override
    public void update(PedidoRestaurante entidad) {
        repository.save(entidad);
    }

    @Override
    public void remove(PedidoRestaurante entidad) {
        repository.delete(entidad);
    }

    @Override
    public PedidoRestaurante findById(Long aLong) {
        return repository.findOne(aLong);
    }

    @Override
    public Iterable<PedidoRestaurante> findAll() {
        return repository.findAll();
    }
}

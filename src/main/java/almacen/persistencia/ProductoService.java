package almacen.persistencia;

import almacen.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistance.CrudService;

@Service("productoService")
public class ProductoService implements CrudService<Producto, Long> {
    @Autowired
    private ProductoRepository  repository;

    @Override
    public void add(Producto producto) {
        repository.save(producto);
    }

    @Override
    public void update(Producto producto) {
        repository.save(producto);
    }

    @Override
    public void remove(Producto producto) {
        repository.delete(producto);
    }

    @Override
    public Producto findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Iterable<Producto> findAll() {
        return repository.findAll();
    }
}

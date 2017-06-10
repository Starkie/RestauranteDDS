package almacen.persistance;

import almacen.model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistance.CrudService;

@Service("proveedorService")
public class ProveedorService implements CrudService<Proveedor, Long>{

    @Autowired
    private ProveedorRepository repository;

    @Override
    public void add(Proveedor proveedor) {
        repository.save(proveedor);
    }

    @Override
    public void update(Proveedor proveedor) {
        repository.save(proveedor);
    }

    @Override
    public void remove(Proveedor proveedor) {
        repository.delete(proveedor);
    }

    @Override
    public Proveedor findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Iterable<Proveedor> findAll() {
        return repository.findAll();
    }
}

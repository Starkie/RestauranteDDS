package persistance;
import model.Plato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("platoService")
public class PlatoService implements CrudService<Plato,Long>{
    @Autowired
    private PlatoRepository repository;

    @Override
    public void add(Plato entidad) {
        repository.save(entidad);
    }

    @Override
    public void update(Plato entidad) {
        repository.save(entidad);
    }

    @Override
    public void remove(Plato entidad) {
        repository.delete(entidad);
    }

    @Override
    public Plato findById(Long index) {
        return repository.findOne(index);
    }

    @Override
    public Iterable<Plato> findAll() {
        return repository.findAll();
    }
}

package persistance;

import model.Reclamacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReclamacionService implements CrudService<Reclamacion,Long> {
    @Autowired
    private ReclamacionRepository repository;

    @Override
    public void add(Reclamacion entidad) {
        repository.save(entidad);
    }

    @Override
    public void update(Reclamacion entidad) {
        repository.save(entidad);
    }

    @Override
    public void remove(Reclamacion entidad) {
        repository.save(entidad);
    }

    @Override
    public Reclamacion findById(Long integer) {
        return null;
    }

    @Override
    public Iterable<Reclamacion> findAll() {
        return repository.findAll();
    }
}

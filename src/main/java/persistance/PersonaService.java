package persistance;

import model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personaService")
public class PersonaService implements CrudService<Persona,Integer>{
    @Autowired
    private PersonaRepository repository;

    @Override
    public void add(Persona entidad) {
        repository.save(entidad);
    }

    @Override
    public void update(Persona entidad) { repository.save(entidad);}

    @Override
    public void remove(Persona entidad) {
        repository.save(entidad);
    }

    @Override
    public Persona findById(Integer i) {
        return null;
    }

    @Override
    public Iterable<Persona> findAll() {
        return repository.findAll();
    }
}

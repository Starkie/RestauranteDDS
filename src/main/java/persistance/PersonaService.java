package persistance;

import domain.Persona;
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
        repository.delete(entidad);
    }

    @Override
    public Persona findById(Integer i) {
        return repository.findOne(i);
    }

    @Override
    public Iterable<Persona> findAll() {
        return repository.findAll();
    }

    public Persona findByName(String nombre) {
        return repository.findByNombre(nombre);
    }
}

package persistance;

import model.Alimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("alimentoService")
public class AlimentoService implements CrudService<Alimento, Long> {
    @Autowired
    private AlimentoRepository repository;


    public void add(Alimento alimento) {
        repository.save(alimento);
    }

    @Override
    public void update(Alimento entidad) {
        repository.save(entidad);
    }

    @Override
    public void remove(Alimento entidad) {
        repository.delete(entidad);
    }

    @Override
    public Alimento findById(Long aLong) {
        return null;
    }

    @Override
    public Iterable<Alimento> findAll() {
        return repository.findAll();
    }

    public Alimento findByName(String nombre) {
        return  repository.findByNombre(nombre);
    }


}

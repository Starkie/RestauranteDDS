package persistance;

import model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("categoriaService")
public class CategoriaService implements CrudService<Categoria, Long>{
    @Autowired
    CategoriaRepository categoriaRepository;

    @Transactional
    public void add(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void update(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void remove(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }

    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findOne(id);
    }

    @Override
    public Iterable<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Transactional
    public Categoria findByName(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }
}

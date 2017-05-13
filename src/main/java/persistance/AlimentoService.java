package persistance;

import model.Alimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("alimento")
public class AlimentoService  {
    @Autowired
    private AlimentoRepository repository;

    public void add(Alimento alimento) {
        repository.save(alimento);
    }

    public Alimento findByName(String nombre) {
        return  repository.findByNombre(nombre);
    }

}

package restaurante.business.modelo.Patron_Decorador;

import domain.Alimento;
import restaurante.domain.Plato;

import javax.persistence.Entity;

@Entity
public class ComplementoTernera extends DecoradorComplemento{

    public ComplementoTernera() {
            super(1.50,120,"Ternera asada",0,
                    0,1);
    }

    public ComplementoTernera(Plato p) {
        super(p,1.50,120,"Ternera asada",0,
                0,1,new Alimento("Ternera"));
    }

    @Override
    public int getNumeroComplementosTernera() {
        return 1 + plato.getNumeroComplementosTernera();
    }

}

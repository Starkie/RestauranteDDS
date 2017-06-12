package restaurante.business.modelo.Patron_Decorador;

import domain.Alimento;
import restaurante.domain.Plato;

import javax.persistence.Entity;

@Entity
public class ComplementoPollo extends DecoradorComplemento{

    public ComplementoPollo() {
        super(1.0,100,"Pollo Crujiente",
                0,1,0);
    }

    public ComplementoPollo(Plato p) {
        super(p,1.0,100,"Pollo Crujiente",
                0,1,0,new Alimento("Pollo"));
    }

    @Override
    public int getNumeroComplementosPollo() {
        return 1 + plato.getNumeroComplementosPollo();
    }

}


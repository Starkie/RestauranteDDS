package restaurante.business.modelo.Patron_Decorador;

import domain.Alimento;
import restaurante.domain.Plato;

import javax.persistence.Entity;

@Entity
public class ComplementoGamba extends DecoradorComplemento{

    public ComplementoGamba() {
        super(1.50,90,"Gambas crujientes",1,
                0,0);
    }

    public ComplementoGamba(Plato p) {
        super(p,1.50,90,"Gambas crujientes",
                1,0,0,new Alimento("Gambas"));
    }
    
    @Override
    public int getNumeroComplementosGamba() {
        return 1 + plato.getNumeroComplementosGamba();
    }

}

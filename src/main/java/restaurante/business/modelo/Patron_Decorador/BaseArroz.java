package restaurante.business.modelo.Patron_Decorador;

import restaurante.domain.Plato;

import javax.persistence.Entity;

@Entity
public class BaseArroz extends Plato {

    public BaseArroz(){
        super(2.5,200,"Delicioso arroz tres delicias",0,0,0);
    }
}


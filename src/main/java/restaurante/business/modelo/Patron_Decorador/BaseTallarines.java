package restaurante.business.modelo.Patron_Decorador;

import restaurante.domain.Plato;

import javax.persistence.Entity;

@Entity
public class BaseTallarines extends Plato {
    
    public BaseTallarines(){
        super(3.0,230,"Tallarines Pad Mei",
                0,0,0);
    }

}

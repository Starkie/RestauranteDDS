package restaurante.modelo.Patron_Decorador;

import model.Plato;

import javax.persistence.Entity;

@Entity
public class BaseTallarines extends Plato {
    
    public BaseTallarines(){
        super(3.0,230,"Tallarines Pad Mei",0,0,0);
    }

}

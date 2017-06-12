package restaurante.plato;


import domain.Alimento;
import restaurante.domain.Plato;

import javax.persistence.Entity;

@Entity
public class MockBaseTallarines extends Plato {

    public MockBaseTallarines(){
        super(3.0,230,"Tallarines Pad Mei", 0,0,0, new Alimento("Tallarines"));
    }

}

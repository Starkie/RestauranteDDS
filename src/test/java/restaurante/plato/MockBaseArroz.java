package restaurante.plato;

import domain.Alimento;
import restaurante.domain.Plato;

import javax.persistence.Entity;

@Entity
public class MockBaseArroz extends Plato {

    public MockBaseArroz(){
        super(2.5,200,"Delicioso arroz tres delicias",0,
                0,0,new Alimento("Base arroz"));
    }
}
package restaurante.plato;

import domain.Alimento;
import org.junit.Assert;
import org.junit.Test;
import restaurante.business.modelo.Patron_Decorador.BaseArroz;
import restaurante.business.modelo.Patron_Decorador.ComplementoGamba;
import restaurante.business.modelo.Patron_Decorador.ComplementoPollo;
import restaurante.domain.Plato;

import java.util.List;

public class TestAlimentosPlato {

    @Test
    public void testAlimentosPlato(){
        Plato p = new ComplementoGamba(new ComplementoPollo(simularBaseArroz()));
        List<Alimento> alimentos = p.getAlimentosPlato();
        String[] esperado = new String[]{"Arroz","Pollo","Gambas"};
        for(int i = 0; i< esperado.length; i++){
            Assert.assertEquals(esperado[i],alimentos.get(i).getNombre());
        }
    }

    private Plato simularBaseArroz() {
        Plato p = new BaseArroz();
        p.setAlimento(new Alimento("Arroz"));
        return p;
    }
}

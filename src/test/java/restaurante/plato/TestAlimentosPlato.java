package restaurante.plato;

import domain.Alimento;
import org.junit.Test;
import restaurante.business.modelo.Patron_Decorador.BaseArroz;
import restaurante.business.modelo.Patron_Decorador.ComplementoGamba;
import restaurante.business.modelo.Patron_Decorador.ComplementoPollo;
import restaurante.domain.Plato;

import java.util.List;

public class TestAlimentosPlato {

    @Test
    public void testAlimentosPlato(){
        Plato p = new ComplementoGamba(new ComplementoPollo(new BaseArroz()));
        List<Alimento> alimentos = p.getAlimentosPlato();
    }
}

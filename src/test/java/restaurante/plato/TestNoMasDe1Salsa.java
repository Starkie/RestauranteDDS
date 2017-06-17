package restaurante.plato;

import org.junit.Test;
import restaurante.business.modelo.Patron_Decorador.*;
import restaurante.domain.Plato;

public class TestNoMasDe1Salsa {
    @Test(expected = SalsaException.class)
    public void crearConDosSalsas() throws Exception {
        Plato plato = new SalsaOstras(new SalsaCacahuetes(new BaseArroz()));
    }

    @Test(expected = SalsaException.class)
    public void añadirSegundaSalsa() throws Exception {
        Plato plato = new SalsaOstras(new ComplementoGamba(new BaseArroz()));
        plato = new SalsaCacahuetes(plato);
    }
}


package restaurante.plato;

import model.Plato;
import org.junit.Test;
import restaurante.modelo.Patron_Decorador.*;

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

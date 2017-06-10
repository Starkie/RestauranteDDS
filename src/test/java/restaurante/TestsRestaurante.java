package restaurante;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import restaurante.pedido.TestPedido;
import restaurante.persistencia.TestPedidoRestauranteService;
import restaurante.persistencia.TestPersonaService;
import restaurante.persistencia.TestPlatoService;
import restaurante.persistencia.TestReclamacionService;
import restaurante.plato.TestElaboracionPlato;
import restaurante.plato.TestNoMasDe1Salsa;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestPedido.class, TestPedidoRestauranteService.class, TestPersonaService.class, TestPlatoService.class,
        TestReclamacionService.class, TestElaboracionPlato.class, TestNoMasDe1Salsa.class})
public class TestsRestaurante {
}

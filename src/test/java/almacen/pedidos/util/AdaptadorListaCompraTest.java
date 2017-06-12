package almacen.pedidos.util;

import almacen.domain.Producto;
import almacen.domain.UnidadesCantidad;
import almacen.pedidos.domain.ListaCompra;
import almacen.pedidos.domain.ListaCompuesto;
import almacen.pedidos.domain.ListaElemento;
import domain.Alimento;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorListaCompraTest {

    ListaCompra base;
    Producto p, p2;
    @Before
    public void setUp() {
        base = new ListaCompuesto("Lista 1", "Ejemplo");

        Alimento alimento = new Alimento();
        p = new Producto("Prod1", alimento, 10.0, 2, UnidadesCantidad.KG);
        p2 = new Producto("Prod2", alimento, 5.0, 1, UnidadesCantidad.LITRO);

        ListaCompra elemento = new ListaElemento(p, 5);

        ListaCompra elemento2 = new ListaElemento(p2, 8);

        base.add(elemento);
        base.add(elemento2);

        ListaCompra lista2 = new ListaCompuesto("Lista 2", "Ejemplo");
        ListaCompra elemento3 = new ListaElemento(p, 2);
        lista2.add(elemento3);

        base.add(lista2);
    }


    @Test
    public void ShouldReturnAllElements() throws Exception {
        List<ElementoAdaptado> listExpected = new ArrayList<>();
        listExpected.add(new ElementoAdaptado(p, 5));
        listExpected.add(new ElementoAdaptado(p2, 8));
        listExpected.add(new ElementoAdaptado(p, 2));

        List<ElementoAdaptado> listActual = AdaptadorListaCompra.adaptarListaCompra(base);

        for(int i = 0; i < listExpected.size(); i++) {
            Assert.assertEquals("ListaActual coincide con listExpeced", listExpected.get(i), listActual.get(i));
        }
    }

}
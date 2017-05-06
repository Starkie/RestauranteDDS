package almacen.modelo.ListaCompra;

import org.junit.Assert;
import org.junit.Test;

public class ListaCompraTest {
    @Test
    public void ShouldReturnSumOfAllElementsPrice() throws Exception {

        ListaCompra base = new ListaCompuesto(1, "Lista 1", "Ejemplo");
        Assert.assertEquals("El precio de la lista vacia es 0", 0.0, base.getPrecio(), 0.1);

        ListaCompra elemento = new ListaElemento(1, "Elemento 1", "Elemento 1", 300);
        Assert.assertEquals("El precio de elemento1 es 3 €", 3.0, elemento.getPrecio(), 0.1);

        ListaCompra elemento2 = new ListaElemento(2, "Elemento 2", "Elemento 2", 800);

        base.add(elemento);
        base.add(elemento2);

        Assert.assertEquals("El precio de Lista 1 con 2 elementos es 11 €", 11.0, base.getPrecio(), 0.1);

        ListaCompra lista2 = new ListaCompuesto(4, "Lista 2", "Ejemplo");
        ListaCompra elemento3 = new ListaElemento(5, "Elemento 3", "Elemento 3", 600);
        lista2.add(elemento3);

        base.add(lista2);
        Assert.assertEquals("El precio de Lista 1 con 2 elementos y una lista es 17 €", 17.0, base.getPrecio(), 0.1);

    }

}
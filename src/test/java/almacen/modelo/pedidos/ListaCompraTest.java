package almacen.modelo.pedidos;

import org.junit.Assert;
import org.junit.Test;

public class ListaCompraTest {
    @Test
    public void ShouldReturnSumOfAllElementsPrice() throws Exception {

        ListaCompra base = new ListaCompuesto("Lista 1", "Ejemplo");
        Assert.assertEquals("El precio de la lista vacia es 0", 0.0, base.getPrecio(), 0.1);

        ListaCompra elemento = new ListaElemento("Elemento 1", "Elemento 1", 1, 300);
        Assert.assertEquals("El precio de elemento1 es 3 €", 3.0, elemento.getPrecio(), 0.1);

        ListaCompra elemento2 = new ListaElemento("Elemento 2", "Elemento 2", 2, 800);

        base.add(elemento);
        base.add(elemento2);

        Assert.assertEquals("El precio de Lista 1 con 2 elementos es 19 €", 19.0, base.getPrecio(), 0.1);

        ListaCompra lista2 = new ListaCompuesto("Lista 2", "Ejemplo");
        ListaCompra elemento3 = new ListaElemento("Elemento 3", "Elemento 3", 2, 600);
        lista2.add(elemento3);

        base.add(lista2);
        Assert.assertEquals("El precio de Lista 1 con 2 elementos y una lista es 31 €", 31.0, base.getPrecio(), 0.1);

        base.remove(elemento);
        Assert.assertEquals("Al eliminar elemento, el precio es de 28€", 28.0, base.getPrecio(), 0.1);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void ShouldThrowUnsupportedOperationOnAddInElement() throws Exception {
        ListaCompra elemento = new ListaElemento("elemento", "elemento", 2, 200);
        ListaCompra elemento2 = new ListaElemento("elemento", "elemento", 2, 200);
        elemento.add(elemento2);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void ShouldThrowUnsupportedOperationOnRemoveInElement() throws Exception {
        ListaCompra elemento = new ListaElemento("elemento", "elemento", 2, 200);
        ListaCompra elemento2 = new ListaElemento("elemento", "elemento", 2, 200);
        elemento.remove(elemento2);
    }

}
package almacen.pedidos.model;

import almacen.model.Producto;
import almacen.model.UnidadesCantidad;
import model.Alimento;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaCompraTest {

    @Test
    public void ShouldReturnSumOfAllElementsPrice() throws Exception {

        Alimento alimento = new Alimento();
        Producto p = new Producto("Prod1", alimento, 3.0, 2, UnidadesCantidad.KG);
        Producto p2 = new Producto("Prod2", alimento, 8.0, 1, UnidadesCantidad.LITRO);


        ListaCompra base = new ListaCompuesto("Lista 1", "Ejemplo");
        Assert.assertEquals("El precio de la lista vacia es 0", 0.0, base.getPrecio(), 0.1);

        ListaCompra elemento = new ListaElemento(p, 1);
        Assert.assertEquals("El precio de elemento1 es 3 €", 3.0, elemento.getPrecio(), 0.1);

        ListaCompra elemento2 = new ListaElemento(p2, 2);

        base.add(elemento);
        base.add(elemento2);

        Assert.assertEquals("El precio de Lista 1 con 2 elementos es 19 €", 19.0, base.getPrecio(), 0.1);

        Producto p3 = new Producto("Prod3", alimento, 6.0, 1, UnidadesCantidad.KG);

        ListaCompra lista2 = new ListaCompuesto("Lista 2", "Ejemplo");
        ListaCompra elemento3 = new ListaElemento(p3, 2);
        lista2.add(elemento3);

        base.add(lista2);
        Assert.assertEquals("El precio de Lista 1 con 2 elementos y una lista es 31 €", 31.0, base.getPrecio(), 0.1);

        base.remove(elemento);
        Assert.assertEquals("Al eliminar elemento, el precio es de 28€", 28.0, base.getPrecio(), 0.1);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void ShouldThrowUnsupportedOperationOnAddInElement() throws Exception {
        ListaCompra elemento = new ListaElemento();
        ListaCompra elemento2 = new ListaElemento();
        elemento.add(elemento2);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void ShouldThrowUnsupportedOperationOnRemoveInElement() throws Exception {
        ListaCompra elemento = new ListaElemento();
        ListaCompra elemento2 = new ListaElemento();
        elemento.remove(elemento2);
    }

    @Test
    public void ShouldIterateOverAllElements() {
        ListaCompra base = new ListaCompuesto("Lista 1", "Ejemplo");

        Alimento alimento = new Alimento();
        Producto p = new Producto("Prod1", alimento, 10.0, 2, UnidadesCantidad.KG);
        Producto p2 = new Producto("Prod2", alimento, 5.0, 1, UnidadesCantidad.LITRO);

        ListaCompra elemento = new ListaElemento(p, 5);

        ListaCompra elemento2 = new ListaElemento(p2, 8);

        base.add(elemento);
        base.add(elemento2);

        ListaCompra lista2 = new ListaCompuesto("Lista 2", "Ejemplo");
        ListaCompra elemento3 = new ListaElemento(p, 2);
        lista2.add(elemento3);

        base.add(lista2);

        ArrayList<String> actualOutput = new ArrayList<>();

        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("Lista: Lista 1 - Ejemplo - 110.0€");
        expectedOutput.add("> 5x - Prod1 - Ud: 10.0€");
        expectedOutput.add("> 8x - Prod2 - Ud: 5.0€");
        expectedOutput.add("Lista: Lista 2 - Ejemplo - 20.0€");
        expectedOutput.add("> 2x - Prod1 - Ud: 10.0€");


        Iterator<ListaCompra> listaCompraIterator =  base.createIterator();
        actualOutput.add(base.toString());

        listaCompraIterator.forEachRemaining(el -> actualOutput.add(el.toString()));

        Assert.assertEquals("Comprobar que la salida es la misma", expectedOutput, actualOutput );

    }

}
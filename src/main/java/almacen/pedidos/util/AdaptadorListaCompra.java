package almacen.pedidos.util;

import almacen.pedidos.domain.ListaCompra;
import almacen.pedidos.domain.ListaElemento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdaptadorListaCompra {
    public static List<ElementoAdaptado> adaptarListaCompra(ListaCompra listaCompra) {
        ArrayList<ElementoAdaptado> listaFilas = new ArrayList<>();
        Iterator<ListaCompra> listaCompraIterator = listaCompra.createIterator();
        listaCompraIterator.forEachRemaining(el ->
                {
                    if (el instanceof ListaElemento) {
                        listaFilas.add(
                                new ElementoAdaptado(
                                        ((ListaElemento) el).getProducto(),
                                        el.getUnidades()
                                )
                        );
                    }
                }
        );
        return listaFilas;
    }

}

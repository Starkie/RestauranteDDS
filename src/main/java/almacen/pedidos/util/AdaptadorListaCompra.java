package almacen.pedidos.util;

import almacen.pedidos.model.ListaCompra;
import almacen.pedidos.model.ListaElemento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdaptadorListaCompra {
    public static List<FilaTabla> adaptarListaCompra(ListaCompra listaCompra) {
        ArrayList<FilaTabla> listaFilas = new ArrayList<>();
        Iterator<ListaCompra> listaCompraIterator = listaCompra.createIterator();
        listaCompraIterator.forEachRemaining(el ->
                {
                    if (el instanceof ListaElemento) {
                        listaFilas.add(
                                new FilaTabla(
                                        ((ListaElemento) el).getProducto(),
                                        ((ListaElemento) el).getUnidades()
                                )
                        );
                    }
                }
        );
        return listaFilas;
    }

}

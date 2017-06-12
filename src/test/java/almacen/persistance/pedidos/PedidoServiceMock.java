package almacen.persistance.pedidos;

import almacen.controllers.ProductoAlmacenController;
import almacen.model.ProductoAlmacen;
import almacen.controllers.ProductoAlmacenControllerMock;
import almacen.pedidos.model.Pedido;
import almacen.pedidos.util.AdaptadorListaCompra;
import almacen.pedidos.util.FilaTabla;
import almacen.persistance.pedidos.PedidoService;

import java.util.ArrayList;
import java.util.List;

public class PedidoServiceMock extends PedidoService {
    List<Pedido> listaPedidos = new ArrayList<>();
    ProductoAlmacenController productoAlmacenController = ProductoAlmacenControllerMock.getInstance();

    @Override
    public void add(Pedido p) {
        listaPedidos.add(p);
        List<FilaTabla> filaTablas = AdaptadorListaCompra.adaptarListaCompra(p.getLista());
        filaTablas.forEach(fila -> productoAlmacenController.guardarProducto(new ProductoAlmacen(fila.getProducto(), fila.getUnidades())));
    }

}

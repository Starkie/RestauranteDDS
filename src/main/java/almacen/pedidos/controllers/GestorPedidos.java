package almacen.pedidos.controllers;

import almacen.controllers.ProductoAlmacenController;
import almacen.model.Producto;
import almacen.model.ProductoAlmacen;
import almacen.pedidos.model.*;
import almacen.pedidos.util.AdaptadorListaCompra;
import almacen.pedidos.util.FilaTabla;
import almacen.persistance.pedidos.PedidoService;
import persistance.AppContext;

import java.util.ArrayList;
import java.util.List;

public class GestorPedidos {
    private static GestorPedidos gestorPedidos;
    private static PedidoService pedidoService;

    private GestorPedidos() {
        this.gestorPedidos = this;
        this.pedidoService = (PedidoService) AppContext.getBean("pedidoService");
    }

    public static GestorPedidos getInstance() {
        if(gestorPedidos == null) {
            gestorPedidos = new GestorPedidos();
        }
        return gestorPedidos;
    }

    public Pedido crearPedido() {
        ListaCompra listaCompra = new ListaCompuesto("Pedido", "Lista Productos");
        Pedido p = new Pedido(listaCompra);
        guardarPedido(p);
        return p;
    }

    public Pedido crearPedido(List<Producto> productos) {
        ListaCompra listaCompra = new ListaCompuesto("Pedido", "Lista Productos");
        for(Producto p : productos) {
            listaCompra.add(new ListaElemento(p, 1 ));
        }
        Pedido p = new Pedido(listaCompra);
        guardarPedido(p);
        return p;
    }

    public void guardarPedido(Pedido p) {
        pedidoService.add(p);
    }

    public Pedido crearPedido(ListaCompra listaCompra) {
        Pedido p = new Pedido(listaCompra);
        guardarPedido(p);
        return p;
    }

    public void addToPedido(Pedido pedido, ListaCompra lista) {
        pedido.addToPedido(lista);
    }

    public void addToPedido(Pedido pedido, Producto producto) {
        pedido.addToPedido(new ListaElemento(producto, 1));
    }

    public void addToPedido(Pedido pedido, List<Producto> productos) {
        for(Producto p : productos) {
            addToPedido(pedido, p);
        }
    }

    public void addProductoToPedido(Pedido pedido, Producto producto) {
        ListaElemento listaElemento = new ListaElemento(producto, 1);
        addToPedido(pedido, listaElemento);
    }

    public void addProductoToPedido(Pedido pedido, Producto producto, int unidades) {
        ListaElemento listaElemento = new ListaElemento(producto, unidades );
        addToPedido(pedido, listaElemento);
    }

    public void confirmarPedido(Pedido pedido) throws AlmacenException {
        pedido.confirmarPedido();
        guardarPedido(pedido);
    }

    public void recibirPedido(Pedido pedido) throws AlmacenException {
        pedido.recibirPedido();
        ProductoAlmacenController productoAlmacenController = ProductoAlmacenController.getInstance();
        List<FilaTabla> filaTablas = AdaptadorListaCompra.adaptarListaCompra(pedido.getLista());
        filaTablas.forEach(fila -> productoAlmacenController.actualizarStock(fila.getProducto(), fila.getUnidades()));
        guardarPedido(pedido);

    }

    public void cancelarPedido(Pedido pedido) throws AlmacenException {
        pedido.cancelarPedido();
        guardarPedido(pedido);
    }

    public ArrayList<Pedido> getAllPedidos() {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        pedidoService.findAll().forEach(p -> pedidos.add(p));
        return pedidos;
    }

}

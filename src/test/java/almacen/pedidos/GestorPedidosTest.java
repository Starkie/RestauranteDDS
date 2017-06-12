package almacen.pedidos;

import almacen.controllers.ProductoAlmacenController;
import almacen.controllers.ProductoAlmacenControllerMock;
import almacen.model.Producto;
import almacen.model.ProductoAlmacen;
import almacen.model.UnidadesCantidad;
import almacen.pedidos.controllers.GestorPedidos;
import almacen.pedidos.model.ListaCompra;
import almacen.pedidos.model.ListaCompuesto;
import almacen.pedidos.model.ListaElemento;
import almacen.pedidos.model.Pedido;
import almacen.persistance.pedidos.PedidoServiceMock;
import almacen.persistance.pedidos.PedidoService;
import model.Alimento;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GestorPedidosTest {

    @Test
    public void crearPedido() throws Exception {
        GestorPedidos gestorPedidos = GestorPedidoMock.getInstance();
        Alimento a1 = new Alimento("Manzana", "Fruta");
        Producto p1 = new Producto("Producto 1", a1, 3, 1, UnidadesCantidad.KG);
        Producto p2 = new Producto("Producto 2", a1, 5, 2, UnidadesCantidad.LITRO);
        List<Producto> listaProductos = new ArrayList<>();

        listaProductos.add(p1);
        listaProductos.add(p2);

        Pedido pedido = gestorPedidos.crearPedido(listaProductos);

        Producto p3 = new Producto("Producto 3", a1, 8, 1, UnidadesCantidad.Unidades);

        gestorPedidos.addToPedido(pedido, p3);

        Producto p4 = new Producto("Producto 4", a1, 8, 1, UnidadesCantidad.Unidades);

        List<Producto> listaProductos2 = new ArrayList<>();
        listaProductos2.add(p4);
        Pedido pedido2 = gestorPedidos.crearPedido(listaProductos2);

        //Aquí reutilizamos la ListaCompra que contiene pedido, para demostrar el funcionamiento del patrón compuesto.
        gestorPedidos.addToPedido(pedido2, pedido.getLista());

        Assert.assertEquals("El precio total de la lista de la compra debe ser ", 24, pedido2.getPrecio(), 0.01);

    }

     @Test
    public void Should_Update_Stock_When_Receiving_Pedido() throws Exception{
        Producto producto = new Producto("Prod1", new Alimento("Ali"), 2, 1, UnidadesCantidad.KG);
        ProductoAlmacen productoAlmacen = new ProductoAlmacen(producto, 2);

        ListaCompra listaCompra = new ListaCompuesto("asdf", "fdas");
        listaCompra.add(new ListaElemento(producto, 2));
        GestorPedidos gestorPedidos = GestorPedidoMock.getInstance();
        Pedido pedido = gestorPedidos.crearPedido(listaCompra);
        gestorPedidos.confirmarPedido(pedido);
        gestorPedidos.recibirPedido(pedido);

         ProductoAlmacenController productoAlmacenController = ProductoAlmacenController.getInstance();
         ProductoAlmacen p2 = productoAlmacenController.buscarPorProducto(producto);

         Assert.assertEquals("Comprobamos que el stock es 4", 4, p2.getStock());
    }

}

class GestorPedidoMock extends GestorPedidos {

    private GestorPedidoMock(PedidoService pedidoService, ProductoAlmacenController productoAlmacenController) {
        super(pedidoService, productoAlmacenController);
    }

    public static GestorPedidos getInstance() {
        if(gestorPedidos == null) {
            PedidoService pedidoService = new PedidoServiceMock();
            ProductoAlmacenController productoAlmacenController = ProductoAlmacenControllerMock.getInstance();
            gestorPedidos = new GestorPedidoMock(pedidoService, productoAlmacenController);
        }
        return gestorPedidos;
    }

}


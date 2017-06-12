package almacen.pedidos;

import almacen.business.controllers.ProductoAlmacenController;
import almacen.controllers.ProductoAlmacenControllerMock;
import almacen.domain.Producto;
import almacen.domain.ProductoAlmacen;
import almacen.domain.UnidadesCantidad;
import almacen.pedidos.controllers.GestorPedidos;
import almacen.pedidos.domain.*;
import almacen.persistance.pedidos.PedidoService;
import almacen.persistance.pedidos.PedidoServiceMock;
import domain.Alimento;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class GestorPedidosTest {

    @Test
    public void Should_Create_Pedido_From_Product_List() throws Exception {
        GestorPedidos gestorPedidos = GestorPedidoMock.getInstance();
        Alimento a1 = new Alimento("Manzana");
        Producto p1 = new Producto("Producto 1", a1, 3, 1, UnidadesCantidad.KG);
        Producto p2 = new Producto("Producto 2", a1, 5, 2, UnidadesCantidad.LITRO);
        List<Producto> listaProductos = new ArrayList<>();

        listaProductos.add(p1);
        listaProductos.add(p2);

        Pedido pedido = gestorPedidos.crearPedido(listaProductos);

        Assert.assertEquals("El precio total de la lista de la compra debe ser ", 8.0,
                pedido.getPrecio(), 0.01);
    }

    @Test
    public void Should_Create_Pedido_From_Existing_Pedido() throws Exception {

        GestorPedidos gestorPedidos = GestorPedidoMock.getInstance();
        Alimento a1 = new Alimento("Manzana");
        Producto p1 = new Producto("Producto 1", a1, 3, 1, UnidadesCantidad.KG);
        Producto p2 = new Producto("Producto 2", a1, 5, 2, UnidadesCantidad.LITRO);
        List<Producto> listaProductos = new ArrayList<>();

        listaProductos.add(p1);
        listaProductos.add(p2);

        Pedido pedido = gestorPedidos.crearPedido(listaProductos);

        Pedido pedido1 = gestorPedidos.crearPedido(pedido.getLista());

        Assert.assertEquals("El precio total del pedido1 de debe ser ", 8.0,
                pedido1.getPrecio(), 0.01);
    }

    @Test
    public void Should_Confirm_Pedido() throws Exception {
        GestorPedidos gestorPedidos = GestorPedidoMock.getInstance();
        Alimento a1 = new Alimento("Manzana");
        Producto p1 = new Producto("Producto 1", a1, 3, 1, UnidadesCantidad.KG);
        Producto p2 = new Producto("Producto 2", a1, 5, 2, UnidadesCantidad.LITRO);
        List<Producto> listaProductos = new ArrayList<>();

        listaProductos.add(p1);
        listaProductos.add(p2);

        Pedido pedido = gestorPedidos.crearPedido(listaProductos);
        gestorPedidos.confirmarPedido(pedido);
    }

    @Test(expected = AlmacenException.class)
    public void Should_Not_Confirm_Pedido() throws Exception {
        GestorPedidos gestorPedidos = GestorPedidoMock.getInstance();
        Alimento a1 = new Alimento("Manzana");
        Producto p1 = new Producto("Producto 1", a1, 3, 1, UnidadesCantidad.KG);
        Producto p2 = new Producto("Producto 2", a1, 5, 2, UnidadesCantidad.LITRO);
        List<Producto> listaProductos = new ArrayList<>();

        listaProductos.add(p1);
        listaProductos.add(p2);

        Pedido pedido = gestorPedidos.crearPedido(listaProductos);
        gestorPedidos.cancelarPedido(pedido);
        gestorPedidos.confirmarPedido(pedido);
    }


    @Test
    public void Should_Update_Stock_When_Receiving_Pedido() throws Exception{
        Producto producto = new Producto("Prod1", new Alimento("Ali"),
                2, 1, UnidadesCantidad.KG);
        ProductoAlmacen productoAlmacen = new ProductoAlmacen(producto, 2);

        ListaCompra listaCompra = new ListaCompuesto("Lista", "Descripcion");
        listaCompra.add(new ListaElemento(producto, 2));
        GestorPedidos gestorPedidos = GestorPedidoMock.getInstance();
        Pedido pedido = gestorPedidos.crearPedido(listaCompra);
        gestorPedidos.confirmarPedido(pedido);
        gestorPedidos.recibirPedido(pedido);

        ProductoAlmacenController productoAlmacenController = ProductoAlmacenController.getInstance();
        ProductoAlmacen p2 = productoAlmacenController.buscarPorProducto(producto);

        Assert.assertEquals("Comprobamos que el stock es 4", 4, p2.getStock());
    }

    @Test
    public void Should_Not_Update_Stock_When_Receiving_Pedido() throws Exception{
        Producto producto = new Producto("Prod1", new Alimento("Ali"),
                2, 1, UnidadesCantidad.KG);
        ProductoAlmacen productoAlmacen = new ProductoAlmacen(producto, 2);

        ListaCompra listaCompra = new ListaCompuesto("Lista", "Descripcion");
        listaCompra.add(new ListaElemento(producto, 2));
        GestorPedidos gestorPedidos = GestorPedidoMock.getInstance();
        Pedido pedido = gestorPedidos.crearPedido(listaCompra);
        gestorPedidos.cancelarPedido(pedido);

        try {
            gestorPedidos.recibirPedido(pedido);
        } catch (AlmacenException e) {
            System.out.println(e.getMessage());
        }
        ProductoAlmacenController productoAlmacenController = ProductoAlmacenController.getInstance();
        ProductoAlmacen p2 = productoAlmacenController.buscarPorProducto(producto);

        Assert.assertEquals("Comprobamos que el stock es 4", 2, p2.getStock());
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


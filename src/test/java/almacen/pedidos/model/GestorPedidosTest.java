package almacen.pedidos.model;

import almacen.model.Producto;
import almacen.model.Proveedor;
import almacen.model.UnidadesCantidad;
import model.Alimento;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GestorPedidosTest {

    @Test
    public void crearPedido() throws Exception {
        GestorPedidos gestorPedidos = GestorPedidos.getInstance();
        Alimento a1 = new Alimento("Manzana", "Fruta");
        Proveedor pr1 = new Proveedor("Proveedor 1", "C/Malaga", "123456");
        Producto p1 = new Producto("Producto 1",  "Descripción de producto 1",  a1, pr1,300, 1, UnidadesCantidad.KG);
        Producto p2 = new Producto("Producto 2", "Descripción del producto 2", a1, pr1, 500, 2,UnidadesCantidad.LITRO);
        List<Producto> listaProductos = new ArrayList<>();

        listaProductos.add(p1);
        listaProductos.add(p2);

        Pedido pedido = gestorPedidos.crearPedido(listaProductos);

        Producto p3 = new Producto("Producto 3", "Descripción de producto 3", a1, pr1, 800, 1, UnidadesCantidad.Unidades);

        gestorPedidos.addToPedido(pedido, p3);

        Producto p4 = new Producto("Producto 4", "Descripción de producto 4", a1, pr1, 800, 1, UnidadesCantidad.Unidades);

        List<Producto> listaProductos2 = new ArrayList<>();
        listaProductos2.add(p4);
        Pedido pedido2 = gestorPedidos.crearPedido(listaProductos2);

        //Aquí reutilizamos la ListaCompra que contiene pedido, para demostrar el funcionamiento del patrón compuesto.
        gestorPedidos.addToPedido(pedido2, pedido.getLista());

        Assert.assertEquals("El precio total de la lista de la compra debe ser ", 24.0, pedido2.getPrecio(), 0.01);

    }

}
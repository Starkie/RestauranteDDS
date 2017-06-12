package almacen.pedidos.domain;

import almacen.domain.Producto;
import almacen.domain.UnidadesCantidad;
import domain.Alimento;
import org.junit.Before;
import org.junit.Test;

public class PedidoTest {

    Producto p1;
    Producto p2;
    ListaCompra listaCompra;
    Pedido pedido;

    @Before
    public void setUp() {
        Alimento alimento = new Alimento();
        p1 = new Producto("Prod1", alimento, 3.00, 2, UnidadesCantidad.KG);
        p2 = new Producto("Prod2", alimento, 8.00, 2, UnidadesCantidad.KG);
        listaCompra = new ListaElemento(p1, 1 );
        pedido = new Pedido(listaCompra);
    }


    @Test
    public void shouldReturn() throws Exception {
        ListaCompra base = new ListaCompuesto("Lista 1", "Ejemplo");
        ListaCompra elemento = new ListaElemento(p1, 1);

        ListaCompra elemento2 = new ListaElemento(p2, 2);
        base.add(elemento);
        base.add(elemento2);

        Pedido p = new Pedido(base);
    }

    @Test
    public void Should_Not_Throw_EstadoPedidoInvalido_When_Confirming_Pendiente_Order() throws Exception {
        pedido.confirmarPedido();
    }

    @Test
    public void Should_Not_Throw_EstadoPedidoInvalido_When_Receiving_Order() throws Exception {
        pedido.confirmarPedido();
        pedido.recibirPedido();
    }

    @Test
    public void Should_Not_Throw_EstadoPedidoInvalido_When_Canceling_Not_Completed_Order() throws Exception {
        pedido.cancelarPedido();
    }

    @Test(expected = EstadoPedidoInvalidoException.class)
    public void Should_Throw_EstadoPedidoInvalidoException_When_Confirming_Canceled_Order() throws Exception {
        pedido.cancelarPedido();
        pedido.confirmarPedido();
    }

    @Test(expected = EstadoPedidoInvalidoException.class)
    public void Should_Throw_EstadoPedidoInvalidoException_When_Canceling_Completed_Order() throws Exception {
        pedido.confirmarPedido();
        pedido.recibirPedido();
        pedido.cancelarPedido();
    }


}
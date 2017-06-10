package almacen.pedidos.model;

import org.junit.Test;

public class PedidoTest {


    @Test
    public void shouldReturn() throws Exception {
        ListaCompra base = new ListaCompuesto("Lista 1", "Ejemplo");
        ListaCompra elemento = new ListaElemento("Elemento 1", "Elemento 1", 1, 300);

        ListaCompra elemento2 = new ListaElemento("Elemento 2", "Elemento 2", 2, 800);
        base.add(elemento);
        base.add(elemento2);

        Pedido p = new Pedido(base);
    }

    @Test
    public void Should_Update_Stock_When_Receiving_Pedido() throws Exception{
        ListaCompra listaCompra = new ListaElemento("Elemento 1", "Elemento 1", 1, 300);
        Pedido pedido = new Pedido(listaCompra);
        pedido.confirmarPedido();
        pedido.recibirPedido();
    }

    @Test(expected = EstadoPedidoInvalidoException.class)
    public void Should_Throw_EstadoPedidoInvalidoException_When_Confirming_Canceled_Order() throws Exception {
        ListaCompra listaCompra = new ListaElemento("Elemento 1", "Elemento 1", 1, 300);
        Pedido pedido = new Pedido(listaCompra);
        pedido.cancelarPedido();
        pedido.confirmarPedido();
    }

    @Test(expected = EstadoPedidoInvalidoException.class)
    public void Should_Throw_EstadoPedidoInvalidoException_When_Canceling_Completed_Order() throws Exception {
        ListaCompra listaCompra = new ListaElemento("Elemento 1", "Elemento 1", 1, 300);
        Pedido pedido = new Pedido(listaCompra);
        pedido.confirmarPedido();
        pedido.recibirPedido();
        pedido.cancelarPedido();
    }

}
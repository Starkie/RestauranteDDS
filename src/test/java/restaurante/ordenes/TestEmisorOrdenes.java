package restaurante.ordenes;

import model.PedidoRestaurante;
import model.Usuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import restaurante.modelo.Patron_Comando.OrdenCocinar;
import restaurante.modelo.Patron_Decorador.BaseArroz;
import restaurante.modelo.Patron_Decorador.BaseTallarines;
import restaurante.modelo.Patron_Estado.EstadoCancelado;
import restaurante.modelo.Patron_Estado.EstadoPendiente;

import java.util.Iterator;

public class TestEmisorOrdenes {
    private static MockEmisorOrdenes elEmisor;

    @BeforeClass
    public static void setUp(){
        elEmisor = MockEmisorOrdenes.getEmisorOrdenes(false);
    }

    @Test
    public void TestanyadirMockRepartidor(){
        MockRepartidor MockRepartidor = new MockRepartidor();
        Iterator<MockRepartidor> MockRepartidorList = elEmisor.getMockRepartidores().iterator();
        boolean encontrado = false;
        while(MockRepartidorList.hasNext()){
            if(MockRepartidor.equals(MockRepartidorList.next())) encontrado = true;
        }
        if(!encontrado) Assert.fail();
    }

    @Test
    public void TestanyadirCocinero(){
        MockCocinero MockMockCocinero = new MockCocinero();
        Iterator<MockCocinero> MockMockCocineroList = elEmisor.getMockCocineros().iterator();
        boolean encontrado = false;
        while(MockMockCocineroList.hasNext()){
            if(MockMockCocinero.equals(MockMockCocineroList.next())) encontrado = true;
        }
        if(!encontrado) Assert.fail();
    }

    @Test
    public void TestAtenderPedido() throws Exception {
        MockCocinero Cocinero = new MockCocinero();
        MockRepartidor Repartidor = new MockRepartidor();
        PedidoRestaurante  pedidoRestaurante = new PedidoRestaurante(new Usuario("Pedro",2121,"Hola calle","12120"));
        pedidoRestaurante.addPlatoPedido(new BaseArroz());
        simularConfirmarPedido(pedidoRestaurante);
        Assert.assertEquals("Pendiente de Cocina",pedidoRestaurante.getEstado().getDescripcion());

        //El thread automáticamente asigna al MockCocinero el pedido.
        elEmisor.ejecutarThread();
        Assert.assertEquals("Pedido elaborándose en Cocina.",pedidoRestaurante.getEstado().getDescripcion());
        Assert.assertEquals(pedidoRestaurante.getId(),Cocinero.getPedidoAtendiendo().getId());

        Cocinero.finalizarCocinaPedido();
        Assert.assertEquals("Pedido ya preparado en Cocina.",pedidoRestaurante.getEstado().getDescripcion());

        //El thread automáticamente asigna al MockRepartidor el pedido.
        elEmisor.ejecutarThread();
        Assert.assertEquals("Pedido en camino a la dirección proporcionada.",pedidoRestaurante.getEstado().getDescripcion());
        Assert.assertEquals(pedidoRestaurante.getId(),Repartidor.getPedidoAtendiendo().getId());
        Repartidor.finalizarEnvio();

        Assert.assertEquals("Pedido recibido.",pedidoRestaurante.getEstado().getDescripcion());

        //Comprobar que ya no tienen trabajo ni el MockRepartidor ni el MockCocinero
        Assert.assertNull(Repartidor.getPedidoAtendiendo());
        Assert.assertNull(Cocinero.getPedidoAtendiendo());
    }

    @Test
    public void test2PedidosEsperandoCocina(){
        MockCocinero Cocinero = new MockCocinero();
        PedidoRestaurante  pedidoRestaurante = new PedidoRestaurante(new Usuario("Pedro",2121,"Hola calle","12120"));
        pedidoRestaurante.addPlatoPedido(new BaseArroz());
        simularConfirmarPedido(pedidoRestaurante);
        PedidoRestaurante  pedidoRestaurante2 = new PedidoRestaurante(new Usuario("Paco",21,"Hola ","120"));
        pedidoRestaurante.addPlatoPedido(new BaseTallarines());
        simularConfirmarPedido(pedidoRestaurante2);

        //Cocinero registrado y esperando pedidos
        Assert.assertNull(Cocinero.getPedidoAtendiendo());
        Assert.assertEquals(Cocinero,elEmisor.getMockCocineros().get(0));

        //En la cola de pedidos se han registrados dos pedidos
        Assert.assertEquals(2,elEmisor.getOrdenesACocinar().size());

        elEmisor.ejecutarThread();

        //Empieza a atender el primer pedido y en la cola de pedidos por cocinar sólo queda 1
        Assert.assertEquals(pedidoRestaurante.getId(),Cocinero.getPedidoAtendiendo().getId());
        Assert.assertEquals(1,elEmisor.getOrdenesACocinar().size());

        //Finalizamos el actual y se asigna el siguiente, por lo que nos quedan 0 pedidos por atender
        Cocinero.finalizarCocinaPedido();
        elEmisor.ejecutarThread();
        Assert.assertEquals(pedidoRestaurante2.getId(),Cocinero.getPedidoAtendiendo().getId());
        Assert.assertEquals(0,elEmisor.getOrdenesACocinar().size());
    }

    @Test
    public void cancelarOrden(){
        MockCocinero Cocinero = new MockCocinero();
        PedidoRestaurante  pedidoRestaurante = new PedidoRestaurante(new Usuario("Pedro",2121,"Hola calle","12120"));
        pedidoRestaurante.addPlatoPedido(new BaseArroz());
        simularConfirmarPedido(pedidoRestaurante);

        Assert.assertEquals(1,elEmisor.getOrdenesACocinar().size());

        simularCancelarPedido(pedidoRestaurante);

        Assert.assertEquals(0,elEmisor.getOrdenesACocinar().size());
    }

    @After
    public void acabarTest(){
        elEmisor.vaciar();
    }

    private void simularConfirmarPedido(PedidoRestaurante pedidoRestaurante){
        pedidoRestaurante.setEstado(new EstadoPendiente());
        elEmisor.anyadirOrden(new OrdenCocinar(pedidoRestaurante));
    }

    private void simularCancelarPedido(PedidoRestaurante pedidoRestaurante){
        pedidoRestaurante.setEstado(new EstadoCancelado());
        elEmisor.cancelarOrden(pedidoRestaurante);
    }

}

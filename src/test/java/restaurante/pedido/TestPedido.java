package restaurante.pedido;

import model.PedidoRestaurante;
import model.Reclamacion;
import model.Usuario;
import org.junit.Assert;
import org.junit.Test;
import restaurante.modelo.Patron_Decorador.BaseArroz;
import restaurante.modelo.Patron_Estado.EstadoCocinandose;

import java.util.Calendar;

public class TestPedido {

    @Test
    public void testConfirmarPedidoNuevo() throws Exception {
        PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco",232,"Hola","12"));
        pedido.addPlatoPedido(new BaseArroz());
        pedido.confirmarPedido();
        Assert.assertEquals("Pendiente de Cocina",pedido.getEstado().getDescripcion());
    }

    @Test
    public void testCancelarPedido() throws Exception {
        PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco",232,"Hola","12"));
        pedido.addPlatoPedido(new BaseArroz());
        pedido.cancelarPedido();
        Assert.assertEquals("Pedido cancelado por el usuario.",pedido.getEstado().getDescripcion());
    }

    @Test
    public void testReclamarPedidoExcepcion(){
        try {
            PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco", 232, "Hola", "12"));
            pedido.addPlatoPedido(new BaseArroz());
            pedido.confirmarPedido();
            pedido.reclamarRetraso();
        } catch(Exception e){Assert.assertEquals("No puede reclamar hasta que no pasen 30 minutos desde la confirmación de su pedido.",e.getMessage());}
    }

    @Test
    public void testReclamarPedidoNoExcepcion() throws Exception {
            PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco", 232, "Hola", "12"));
            pedido.addPlatoPedido(new BaseArroz());
            pedido.confirmarPedido();
            Calendar calendar = Calendar.getInstance();
            calendar.set(1996,12,11);
            pedido.setHoraConfirmacion(calendar.getTime());
            Reclamacion r = pedido.reclamarRetraso();
            Assert.assertNotNull(r);
    }

    @Test
    public void testCancelarException(){
        try {
            PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco", 232, "Hola", "12"));
            pedido.addPlatoPedido(new BaseArroz());
            pedido.confirmarPedido();
            pedido.setEstado(new EstadoCocinandose());
            pedido.cancelarPedido();
        } catch(Exception e){Assert.assertEquals("El pedido ya está cocinándose y no se puede cancelar.",e.getMessage());}
    }

    @Test
    public void testCofirmarPedidoExcepcion(){
        try {
            PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco", 232, "Hola", "12"));
            pedido.addPlatoPedido(new BaseArroz());
            pedido.confirmarPedido();
            pedido.confirmarPedido();
        } catch(Exception e){Assert.assertEquals("El pedido ya ha sido confirmado.",e.getMessage());}
    }

    public void testCofirmarPedidoExcepcion1Plato(){
        try {
            PedidoRestaurante pedido = new PedidoRestaurante(new Usuario("Paco", 232, "Hola", "12"));
            pedido.confirmarPedido();
        } catch(Exception e){Assert.assertEquals("Un pedido debe contener almenos 1 plato",e.getMessage());}
    }
}
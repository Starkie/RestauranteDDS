package restaurante.modelo.Patron_Estado;

import model.PedidoRestaurante;
import model.Reclamacion;

import java.util.Date;

public class EstadoPorConfirmar extends EstadoPedido{

    public EstadoPorConfirmar() {
        super("Pedido todavía realizándose.");
    }

    @Override
    public void confirmarPedido(PedidoRestaurante pedido) throws Exception {
        pedido.setEstado(new EstadoPendiente());
        pedido.setHoraConfirmacion(new Date()); //Ponemos como hora de confirmación la hora actual
    }

    @Override
    public void cancelarPedido(PedidoRestaurante pedido) throws Exception {
        pedido.setEstado(new EstadoCancelado());
    }

    @Override
    public Reclamacion reclamarRetraso(PedidoRestaurante pedido) throws Exception {
        throw new Exception("El pedido todavía no se ha realizado.");
    }
}

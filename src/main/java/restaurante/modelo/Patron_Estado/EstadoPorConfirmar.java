package restaurante.modelo.Patron_Estado;

import model.PedidoRestaurante;
import model.Reclamacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PorConfirmar")
public class EstadoPorConfirmar extends EstadoPedido {

    public EstadoPorConfirmar() {
        super("Pedido todavía realizándose.");
    }

    @Override
    public void confirmarPedido(PedidoRestaurante pedido) throws Exception {
        pedido.setEstado(new EstadoPendiente());
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

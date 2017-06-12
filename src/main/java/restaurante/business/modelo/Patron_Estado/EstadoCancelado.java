package restaurante.business.modelo.Patron_Estado;

import restaurante.domain.PedidoRestaurante;
import restaurante.domain.Reclamacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cancelado")
public class EstadoCancelado extends EstadoPedido {

    public EstadoCancelado() {
        super("Pedido cancelado por el usuario.");
    }

    @Override
    public void confirmarPedido(PedidoRestaurante pedido) throws Exception {
        throw new Exception("El pedido ya ha sido cancelado.");
    }

    @Override
    public void cancelarPedido(PedidoRestaurante pedido) throws Exception {
        throw new Exception("El pedido ya ha sido cancelado");
    }

    @Override
    public Reclamacion reclamarRetraso(PedidoRestaurante pedido) throws Exception {
        throw new Exception("El pedido ya ha sido cancelado");
    }
}

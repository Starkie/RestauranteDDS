package restaurante.modelo.Patron_Estado;

import model.PedidoRestaurante;
import model.Reclamacion;

public abstract class EstadoPedido {
    private String descripcion;

    public EstadoPedido(String descripcion) {
        this.descripcion = descripcion;
    }

    public abstract void confirmarPedido(PedidoRestaurante pedido) throws Exception;

    public abstract void cancelarPedido(PedidoRestaurante pedido) throws Exception;

    public abstract Reclamacion  reclamarRetraso(PedidoRestaurante pedido) throws Exception;
}

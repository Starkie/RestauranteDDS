package almacen.modelo.pedidos;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date fecha;
    private ListaCompra lista;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    public Pedido(ListaCompra lista) {
        this.lista = lista;
        this.fecha = new Date();
        this.estado = EstadoPedido.PENDIENTE;
    }

    public ListaCompra getLista() {
        return lista;
    }

    public Date getFecha() {
        return fecha;
    }

    public void confirmarPedido() throws EstadoPedidoInvalidoException {
        if(lista != null && this.estado == EstadoPedido.PENDIENTE) {
            this.estado = EstadoPedido.EN_CAMINO;
        }
        else {
            throw new EstadoPedidoInvalidoException("El pedido ya estaba confirmado o finalizado, no se puede confirmar.");
        }
    }

    public void recibirPedido() {
        if(estado == EstadoPedido.EN_CAMINO)
        {
            estado = EstadoPedido.COMPLETO;
        }
        //Si estado == EN_CAMINO Debe modificar las cantidades del producto en almacen
        throw new UnsupportedOperationException("No implementado");
    }

    public void cancelarPedido() throws EstadoPedidoInvalidoException {
        if(estado != EstadoPedido.COMPLETO && estado != EstadoPedido.EN_CAMINO) {
            estado = EstadoPedido.CANCELADO;
        }
        else {
            throw new EstadoPedidoInvalidoException("El pedido ya estaba en camino o completado, no se puede cancelar");
        }
    }
}

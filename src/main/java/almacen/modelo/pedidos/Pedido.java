package almacen.modelo.pedidos;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date fecha;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private ListaCompra lista;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    /**
     * Constructor requerido por Hibernate
     */

    public  Pedido() {}

    public Pedido(ListaCompra lista) {
        this.lista = lista;
        this.fecha = new Date();
        this.estado = EstadoPedido.PENDIENTE;
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

    public double getPrecio() {
        return this.lista.getPrecio();
    }

    public void addToPedido(ListaCompra lista) {
        this.lista.add(lista);
    }


    public ListaCompra getLista() {
        return this.lista;
    }

}

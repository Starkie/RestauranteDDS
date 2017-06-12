package restaurante.modelo.Patron_Estado;

import model.PedidoRestaurante;
import model.Reclamacion;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TipoEstado",discriminatorType = DiscriminatorType.STRING)
public abstract class EstadoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @OneToOne
    private PedidoRestaurante pedidoRestaurante;

    private String descripcion;

    public EstadoPedido() {
    }

    public EstadoPedido(String descripcion) {
        this.descripcion = descripcion;
    }

    public abstract void confirmarPedido(PedidoRestaurante pedido) throws Exception;

    public abstract void cancelarPedido(PedidoRestaurante pedido) throws Exception;

    public abstract Reclamacion reclamarRetraso(PedidoRestaurante pedido) throws Exception;

    public String getDescripcion() {
        return descripcion;
    }
}


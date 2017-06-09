package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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


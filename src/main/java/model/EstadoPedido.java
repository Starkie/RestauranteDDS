package model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="TipoEstado",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class EstadoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "pedidoRestaurente_id")
    private PedidoRestaurante pedidoRestaurante;

    private String descripcion;

    public EstadoPedido(String descripcion) {
        this.descripcion = descripcion;
    }

    public abstract void confirmarPedido(PedidoRestaurante pedido) throws Exception;

    public abstract void cancelarPedido(PedidoRestaurante pedido) throws Exception;

    public abstract Reclamacion  reclamarRetraso(PedidoRestaurante pedido) throws Exception;

    public String getDescripcion() {
        return descripcion;
    }
}


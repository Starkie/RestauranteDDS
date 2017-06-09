package model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reclamacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "pedidoRestaurante_id")
    private PedidoRestaurante pedidoRestaurante;
    private String titulo;
    private String descripcion;
    private Date horaReclamacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="reclamacion_fk")
    private PedidoRestaurante pedidoAsociado;

    public Reclamacion(Date horaReclamacion, PedidoRestaurante pedidoAsociado) {
        this.horaReclamacion = horaReclamacion;
        this.pedidoAsociado = pedidoAsociado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

package model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reclamacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titulo;
    private String descripcion;
    private Date horaReclamacion;

    @OneToOne(cascade = CascadeType.ALL)
    private PedidoRestaurante pedidoRestaurante;

    public Reclamacion(){}

    public Reclamacion(Date horaReclamacion, PedidoRestaurante pedidoRestaurante) {
        this.horaReclamacion = horaReclamacion;
        this.pedidoRestaurante = pedidoRestaurante;
    }

    public Reclamacion(String titulo, String descripcion, Date horaReclamacion, PedidoRestaurante pedidoRestaurante) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.horaReclamacion = horaReclamacion;
        this.pedidoRestaurante = pedidoRestaurante;
    }

    public long getId() {
        return id;
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

    public PedidoRestaurante getPedidoRestaurante() {
        return pedidoRestaurante;
    }

    public Date getHoraReclamacion() {
        return horaReclamacion;
    }

    public void setHoraReclamacion(Date horaReclamacion) {
        this.horaReclamacion = horaReclamacion;
    }

    public void setPedidoRestaurante(PedidoRestaurante pedidoRestaurante) {
        this.pedidoRestaurante = pedidoRestaurante;
    }
}

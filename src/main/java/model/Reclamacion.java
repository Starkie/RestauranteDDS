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

    @OneToOne
    private PedidoRestaurante pedidoRestaurante;

    public Reclamacion(){}

    public Reclamacion(Date horaReclamacion, PedidoRestaurante pedidoRestaurante) {
        this.horaReclamacion = horaReclamacion;
        this.pedidoRestaurante = pedidoRestaurante;
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

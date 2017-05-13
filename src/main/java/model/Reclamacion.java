package model;

import java.util.Date;

public class Reclamacion {
    private String titulo;
    private String descripcion;
    private Date horaReclamacion;
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

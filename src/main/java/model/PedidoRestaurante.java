package model;

import restaurante.modelo.Patron_Estado.EstadoPedido;
import restaurante.modelo.Patron_Estado.EstadoPorConfirmar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoRestaurante {

    private EstadoPedido estado;
    private Date horaConfirmacion;
    private Date horaRecibido;
    private List<Plato> platosPedido;
    private Reclamacion reclamacion;

    public PedidoRestaurante() {
        this.estado = new EstadoPorConfirmar();
        platosPedido = new ArrayList<Plato>();
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Date getHoraConfirmacion() {
        return horaConfirmacion;
    }

    public void setHoraConfirmacion(Date horaConfirmacion) {
        this.horaConfirmacion = horaConfirmacion;
    }

    public Date getHoraRecibido() {
        return horaRecibido;
    }

    public List<Plato> getPlatosPedido() {
        return platosPedido;
    }

    public void setPlatosPedido(List<Plato> platosPedido) {
        this.platosPedido = platosPedido;
    }

    public Reclamacion getReclamacion() {
        return reclamacion;
    }

    public void setReclamacion(Reclamacion reclamacion) {
        this.reclamacion = reclamacion;
    }

    public void setHoraRecibido(Date horaRecibido) {
        this.horaRecibido = horaRecibido;
    }

    public  void confirmarPedido() throws Exception {
        estado.confirmarPedido(this);
    }

    public  void cancelarPedido() throws Exception {
        estado.cancelarPedido(this);
    }

    public void reclamarRetraso() throws Exception {
        setReclamacion(estado.reclamarRetraso(this));
    }

}

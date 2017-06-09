package model;

import restaurante.modelo.Patron_Comando.EmisorOrdenes;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Repartidor extends Persona{
    private boolean disponible;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PedidoRestaurante pedidoAtendiendo;

    public Repartidor() {
    }

    public Repartidor(String nombre, int dni) {
        super(nombre,dni);
        this.disponible=true;
        EmisorOrdenes.getEmisorOrdenes().registrarRepartidor(this);
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void realizarEnvio(PedidoRestaurante pedido){
        pedido.setEstado(new EstadoEnCamino());
        this.pedidoAtendiendo = pedido;
    }

    public void finalizarEnvio(){
        this.pedidoAtendiendo.setEstado(new EstadoEntregado());
        this.pedidoAtendiendo = null;
        this.disponible=true;
    }
}

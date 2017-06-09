package model;

import restaurante.modelo.Patron_Comando.EmisorOrdenes;
import restaurante.modelo.Patron_Comando.OrdenRepartir;
import restaurante.modelo.Patron_Estado.EstadoCocinado;
import restaurante.modelo.Patron_Estado.EstadoCocinandose;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Cocinero extends Persona{
    private boolean disponible;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PedidoRestaurante pedidoAtendiendo;

    public Cocinero(String nombre, int dni) {
        super(nombre, dni);
        this.disponible = true;
        EmisorOrdenes.getEmisorOrdenes().registrarCocinero(this);
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void cocinarPedido(PedidoRestaurante pedido){
        this.pedidoAtendiendo = pedido;
        pedido.setEstado(new EstadoCocinandose());
        for (Plato p:pedido.getPlatosPedido()) {
            //A LA ESPERA DE INTEGRAR CON ALMACÉN
        }
    }

    public void finalizarCocinaPedido(){
        this.pedidoAtendiendo.setEstado(new EstadoCocinado());
        EmisorOrdenes.getEmisorOrdenes().anyadirOrden(new OrdenRepartir(pedidoAtendiendo));
        this.pedidoAtendiendo = null;
        this.disponible=true;
    }
}

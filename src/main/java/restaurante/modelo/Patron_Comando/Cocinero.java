package restaurante.modelo.Patron_Comando;

import model.PedidoRestaurante;
import model.Persona;
import model.Plato;
import restaurante.modelo.Patron_Estado.EstadoCocinado;
import restaurante.modelo.Patron_Estado.EstadoCocinandose;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Cocinero extends Persona {
    private boolean disponible;

    @OneToOne(cascade = CascadeType.ALL)
    private PedidoRestaurante pedidoAtendiendo;

    public Cocinero(){}

    public Cocinero(String nombre, int dni, String contraseña) {
        super(nombre, dni, contraseña);
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
        if(pedidoAtendiendo != null){ this.pedidoAtendiendo.setEstado(new EstadoCocinado());
        EmisorOrdenes.getEmisorOrdenes().anyadirOrden(new OrdenRepartir(pedidoAtendiendo));}
        this.pedidoAtendiendo = null;
        this.disponible=true;
        EmisorOrdenes.getEmisorOrdenes().registrarCocinero(this);
    }

    public PedidoRestaurante getPedidoAtendiendo() {
        return pedidoAtendiendo;
    }

    public void setPedidoAtendiendo(PedidoRestaurante pedidoAtendiendo) {
        this.pedidoAtendiendo = pedidoAtendiendo;
    }
}

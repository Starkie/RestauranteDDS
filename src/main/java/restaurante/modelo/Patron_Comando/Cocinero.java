package restaurante.modelo.Patron_Comando;

import model.PedidoRestaurante;
import model.Persona;
import model.Plato;
import restaurante.modelo.Patron_Estado.EstadoCocinandose;
import restaurante.modelo.Patron_Estado.EstadoFinalizadoCocina;

public class Cocinero extends Persona{
    private boolean disponible;
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
        this.pedidoAtendiendo.setEstado(new EstadoFinalizadoCocina());
        EmisorOrdenes.getEmisorOrdenes().anyadirOrden(new OrdenRepartir(pedidoAtendiendo));
        this.pedidoAtendiendo = null;
        this.disponible=true;
    }
}

package restaurante.modelo.Patron_Comando;

import model.PedidoRestaurante;
import model.Persona;
import restaurante.modelo.Patron_Estado.EstadoEnCamino;
import restaurante.modelo.Patron_Estado.EstadoEntregado;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Repartidor extends Persona {
    private boolean disponible;

    @OneToOne(cascade = CascadeType.ALL)
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

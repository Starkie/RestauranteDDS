package restaurante.modelo.Patron_Comando;

import model.Envio;
import model.PedidoRestaurante;
import model.Persona;
import restaurante.modelo.Patron_Estado.EstadoEnCamino;
import restaurante.modelo.Patron_Estado.EstadoFinalizadoReparto;

public class Repartidor extends Persona{
    private boolean disponible;
    private Envio envioActual;

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
        envioActual = new Envio(this,pedido);
    }

    public void finalizarEnvio(){
        envioActual.getPedido().setEstado(new EstadoFinalizadoReparto());
        envioActual = null;
        this.disponible=true;
    }
}

package restaurante.ordenes;

import restaurante.business.modelo.Patron_Comando.Repartidor;
import restaurante.business.modelo.Patron_Estado.EstadoEntregado;

public class MockRepartidor extends Repartidor{
    public MockRepartidor(){
        this.setDisponible(true);
        this.setPedidoAtendiendo(null);
        MockEmisorOrdenes.getEmisorOrdenes().registrarMockRepartidor(this);
    }
    public void finalizarEnvio(){
        if(getPedidoAtendiendo() != null) getPedidoAtendiendo().setEstado(new EstadoEntregado());
        setPedidoAtendiendo(null);
        setDisponible(true);
        MockEmisorOrdenes.getEmisorOrdenes().registrarMockRepartidor(this);
    }
}

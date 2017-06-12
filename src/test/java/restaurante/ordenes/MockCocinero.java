package restaurante.ordenes;


import restaurante.business.modelo.Patron_Comando.Cocinero;
import restaurante.business.modelo.Patron_Comando.OrdenRepartir;
import restaurante.business.modelo.Patron_Estado.EstadoCocinado;

public class MockCocinero extends Cocinero {
    public MockCocinero() {
        this.setDisponible(true);
        this.setPedidoAtendiendo(null);
        MockEmisorOrdenes.getEmisorOrdenes().registrarMockCocinero(this);
    }
    public void finalizarCocinaPedido(){
        if(getPedidoAtendiendo() != null){ getPedidoAtendiendo().setEstado(new EstadoCocinado());
            MockEmisorOrdenes.getEmisorOrdenes().anyadirOrden(new OrdenRepartir(getPedidoAtendiendo()));}
        setPedidoAtendiendo(null);
        this.setDisponible(true);
        MockEmisorOrdenes.getEmisorOrdenes().registrarMockCocinero(this);
    }
}

package restaurante.modelo;

import model.PedidoRestaurante;
import model.Usuario;
import restaurante.modelo.Patron_Comando.EmisorOrdenes;
import restaurante.modelo.Patron_Comando.OrdenCocinar;

public class ControladorVistaPedido {
    PedidoRestaurante elPedido;
    Usuario elUsuario;
    EmisorOrdenes elEmisor = EmisorOrdenes.getEmisorOrdenes();

    public void NuevoPedido(){
        elPedido = new PedidoRestaurante(elUsuario);
    }

    public void ConfirmarPedido() throws Exception{
        elPedido.confirmarPedido();
        elEmisor.anyadirOrden(new OrdenCocinar(elPedido));
    }

    public void CancelarPedido() throws Exception{
        elPedido.cancelarPedido();
        elEmisor.cancelarOrden(elPedido);
    }
}

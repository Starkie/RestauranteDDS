package restaurante.business.bussiness_controller;

import persistance.ServiceLocator;
import restaurante.business.modelo.Patron_Comando.EmisorOrdenes;
import restaurante.business.modelo.Patron_Comando.OrdenCocinar;
import restaurante.domain.PedidoRestaurante;
import restaurante.domain.Reclamacion;
import restaurante.domain.Usuario;
import restaurante.persistance.PedidoRestauranteService;

public class ControladorPedidoRestaurante {
    private EmisorOrdenes elEmisor;
    private  PedidoRestauranteService pedidoRestauranteService;

    private static ControladorPedidoRestaurante controladorPedidoRestaurante;

    private ControladorPedidoRestaurante(PedidoRestauranteService pedidoRestauranteService) {
        elEmisor = EmisorOrdenes.getEmisorOrdenes();
        this.pedidoRestauranteService = pedidoRestauranteService;
    }

    public static ControladorPedidoRestaurante getControladorPedidoRestaurante(){
        if(controladorPedidoRestaurante == null) {
            PedidoRestauranteService pedidoRestauranteService = ServiceLocator.getPedidoRestauranteService();
            controladorPedidoRestaurante = new ControladorPedidoRestaurante(pedidoRestauranteService);
        }
        return controladorPedidoRestaurante;
    }

    public PedidoRestaurante NuevoPedido(Usuario elUsuario){
        PedidoRestaurante elPedido = new PedidoRestaurante();
        pedidoRestauranteService.add(elPedido);
        elPedido.setUsuario(elUsuario);
        pedidoRestauranteService.update(elPedido);
        return elPedido;
    }

    public void ConfirmarPedido(PedidoRestaurante elPedido) throws Exception{
        elPedido.confirmarPedido();
        elEmisor.anyadirOrden(new OrdenCocinar(elPedido));
    }

    public void CancelarPedido(PedidoRestaurante elPedido) throws Exception{
        //Sólo cancelarPedido() debe lanzar excepción, que se capturará en la vista
        elPedido.cancelarPedido();
        elEmisor.cancelarOrden(elPedido);
        pedidoRestauranteService.update(elPedido);
    }

    public Reclamacion reclamarRetraso(PedidoRestaurante pedidoSeleccionado) throws Exception {
        //Lanza excepción si no se puede reclamar, se captura en la vista.
        if( pedidoSeleccionado.getReclamacion() != null) return pedidoSeleccionado.getReclamacion();
        else{
            return pedidoSeleccionado.reclamarRetraso();
        }
    }
}

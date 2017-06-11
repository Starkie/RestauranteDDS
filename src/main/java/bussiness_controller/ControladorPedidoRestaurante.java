package bussiness_controller;

import model.PedidoRestaurante;
import model.Reclamacion;
import model.Usuario;
import persistance.AppContext;
import persistance.PedidoRestauranteService;
import restaurante.modelo.Patron_Comando.EmisorOrdenes;
import restaurante.modelo.Patron_Comando.OrdenCocinar;

public class ControladorPedidoRestaurante {
    private EmisorOrdenes elEmisor;
    private  PedidoRestauranteService pedidoRestauranteService;

    private static ControladorPedidoRestaurante controladorPedidoRestaurante;

    private ControladorPedidoRestaurante() {
        elEmisor = EmisorOrdenes.getEmisorOrdenes();
        pedidoRestauranteService = (PedidoRestauranteService) AppContext.getBean("pedidoRestauranteService");
    }

    public static ControladorPedidoRestaurante getControladorPedidoRestaurante(){
        if(controladorPedidoRestaurante == null) controladorPedidoRestaurante = new ControladorPedidoRestaurante();
        return controladorPedidoRestaurante;
    }

    public PedidoRestaurante NuevoPedido(Usuario elUsuario){
        PedidoRestaurante elPedido = new PedidoRestaurante(elUsuario);
        pedidoRestauranteService.add(elPedido);
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

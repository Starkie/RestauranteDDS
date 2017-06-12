package bussiness_controller;

import model.PedidoRestaurante;
import model.Plato;
import persistance.AppContext;
import persistance.PedidoRestauranteService;
import persistance.PlatoService;
import restaurante.modelo.Patron_Decorador.*;

public class ControladorPlato {

    private static ControladorPlato controladorPlato;
    private PlatoService platoService;
    private PedidoRestauranteService pedidoRestauranteService;

    private ControladorPlato() {
        platoService = (PlatoService) AppContext.getBean("platoService");
        pedidoRestauranteService = (PedidoRestauranteService) AppContext.getBean("pedidoRestauranteService");
    }

    public static ControladorPlato getControladorPlato(){
        if(controladorPlato == null) controladorPlato = new ControladorPlato();
        return controladorPlato;
    }

    public void crearPlatoArroz(PedidoRestaurante pedido) {
        Plato p = new BaseArroz();
        platoService.add(p);
        pedido.addPlatoPedido(p);
        pedidoRestauranteService.update(pedido);
    }

    public void crearPlatoTallarines(PedidoRestaurante pedido) {
        Plato p = new BaseTallarines();
        platoService.add(p);
        pedido.addPlatoPedido(p);
        pedidoRestauranteService.update(pedido);
    }

    public void cancelarPedido(PedidoRestaurante pedido) throws Exception {
        pedido.cancelarPedido();
        pedidoRestauranteService.update(pedido);
    }

    public void confirmarPedido(PedidoRestaurante pedido) throws Exception {
        pedido.confirmarPedido();
        pedidoRestauranteService.update(pedido);
    }

    public void eliminarPlatoDelPedido(Plato plato,PedidoRestaurante pedidoRestaurante) {
        pedidoRestaurante.deletePlatoPedido(plato);
        platoService.remove(plato);
        pedidoRestauranteService.update(pedidoRestaurante);
    }

    public void anyadirPollo(Plato seleccionado,PedidoRestaurante pedido) {
        eliminarPlatoViejo(seleccionado, pedido);

        Plato nuevo = new ComplementoPollo();
        platoService.add(nuevo);
        ((DecoradorComplemento) nuevo).setPlato(seleccionado);
        pedido.addPlatoPedido(nuevo);
        pedidoRestauranteService.update(pedido);
    }

    public void anyadirTernera(Plato seleccionado, PedidoRestaurante pedido) {
        eliminarPlatoViejo(seleccionado, pedido);

        Plato nuevo = new ComplementoTernera();
        platoService.add(nuevo);
        ((DecoradorComplemento) nuevo).setPlato(seleccionado);
        pedido.addPlatoPedido(nuevo);
        pedidoRestauranteService.update(pedido);
    }

    public void anyadirGambas(Plato seleccionado, PedidoRestaurante pedido) {
        eliminarPlatoViejo(seleccionado, pedido);

        Plato nuevo = new ComplementoGamba();
        platoService.add(nuevo);
        ((DecoradorComplemento) nuevo).setPlato(seleccionado);
        pedido.addPlatoPedido(nuevo);
        pedidoRestauranteService.update(pedido);
    }

    public void anyadirCacahuetes(Plato seleccionado, PedidoRestaurante pedido) throws Exception {
        //Lanza excepción si ya tiene una salsa.
        eliminarPlatoViejo(seleccionado, pedido);

        Plato nuevo = new SalsaCacahuetes();
        platoService.add(nuevo);
        ((DecoradorSalsa) nuevo).setPlato(seleccionado);
        pedido.addPlatoPedido(nuevo);
        pedidoRestauranteService.update(pedido);
    }


    public void anyadirOstras(Plato seleccionado, PedidoRestaurante pedido) throws Exception {
        //Lanza excepción si ya tiene una salsa.
        eliminarPlatoViejo(seleccionado, pedido);

        Plato nuevo = new SalsaOstras();
        platoService.add(nuevo);
        ((DecoradorSalsa) nuevo).setPlato(seleccionado);
        pedido.addPlatoPedido(nuevo);
        pedidoRestauranteService.update(pedido);
    }

    private void eliminarPlatoViejo(Plato seleccionado, PedidoRestaurante pedido) {
        pedido.deletePlatoPedido(seleccionado);
        seleccionado.setPedidoRestaurante(null);
        platoService.update(seleccionado);
        pedidoRestauranteService.update(pedido);
    }
}

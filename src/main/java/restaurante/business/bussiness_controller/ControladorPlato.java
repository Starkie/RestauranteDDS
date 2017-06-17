package restaurante.business.bussiness_controller;

import persistance.AlimentoService;
import persistance.AppContext;
import persistance.ServiceLocator;
import restaurante.business.modelo.Patron_Decorador.*;
import restaurante.domain.PedidoRestaurante;
import restaurante.domain.Plato;
import restaurante.persistance.PedidoRestauranteService;
import restaurante.persistance.PlatoService;

public class ControladorPlato {

    private static ControladorPlato controladorPlato;
    private PlatoService platoService;
    private PedidoRestauranteService pedidoRestauranteService;
    private AlimentoService alimentoService;

    private ControladorPlato(AlimentoService alimentoService, PlatoService platoService, PedidoRestauranteService pedidoRestauranteService) {
        this.alimentoService = alimentoService;
        this.platoService = platoService;
        this.pedidoRestauranteService = pedidoRestauranteService;
    }

    public static ControladorPlato getControladorPlato(){
        if(controladorPlato == null) {
            AlimentoService alimentoService = ServiceLocator.getAlimentoService();
            PlatoService platoService = ServiceLocator.getPlatoService();
            PedidoRestauranteService pedidoRestauranteService = ServiceLocator.getPedidoRestauranteService();
            controladorPlato = new ControladorPlato(alimentoService, platoService, pedidoRestauranteService);
        }
        return controladorPlato;
    }

    public void crearPlatoArroz(PedidoRestaurante pedido) {
        Plato p = new BaseArroz();
        platoService.add(p);
        p.setAlimento(alimentoService.findByName("Arroz"));
        pedido.addPlatoPedido(p);
        pedidoRestauranteService.update(pedido);
    }

    public void crearPlatoTallarines(PedidoRestaurante pedido) {
        Plato p = new BaseTallarines();
        platoService.add(p);
        p.setAlimento(alimentoService.findByName("Tallarines"));
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
        nuevo.setAlimento(alimentoService.findByName("Pollo"));
        ((DecoradorComplemento) nuevo).setPlato(seleccionado);
        pedido.addPlatoPedido(nuevo);
        pedidoRestauranteService.update(pedido);
    }

    public void anyadirTernera(Plato seleccionado, PedidoRestaurante pedido) {
        eliminarPlatoViejo(seleccionado, pedido);

        Plato nuevo = new ComplementoTernera();
        platoService.add(nuevo);
        nuevo.setAlimento(alimentoService.findByName("Ternera"));
        ((DecoradorComplemento) nuevo).setPlato(seleccionado);
        pedido.addPlatoPedido(nuevo);
        pedidoRestauranteService.update(pedido);
    }

    public void anyadirGambas(Plato seleccionado, PedidoRestaurante pedido) {
        eliminarPlatoViejo(seleccionado, pedido);

        Plato nuevo = new ComplementoGamba();
        platoService.add(nuevo);
        nuevo.setAlimento(alimentoService.findByName("Gambas"));
        ((DecoradorComplemento) nuevo).setPlato(seleccionado);
        pedido.addPlatoPedido(nuevo);
        pedidoRestauranteService.update(pedido);
    }

    public void anyadirCacahuetes(Plato seleccionado, PedidoRestaurante pedido) throws Exception {
        //Lanza excepción si ya tiene una salsa.
        eliminarPlatoViejo(seleccionado, pedido);

        Plato nuevo = new SalsaCacahuetes();
        platoService.add(nuevo);
        nuevo.setAlimento(alimentoService.findByName("Cacahuetes"));
        ((DecoradorSalsa) nuevo).setPlato(seleccionado);
        pedido.addPlatoPedido(nuevo);
        pedidoRestauranteService.update(pedido);
    }


    public void anyadirOstras(Plato seleccionado, PedidoRestaurante pedido) throws Exception {
        //Lanza excepción si ya tiene una salsa.
        eliminarPlatoViejo(seleccionado, pedido);

        Plato nuevo = new SalsaOstras();
        platoService.add(nuevo);
        nuevo.setAlimento(alimentoService.findByName("Ostras"));
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

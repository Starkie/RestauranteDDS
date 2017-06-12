package restaurante.ordenes;

import persistance.ServiceLocator;
import restaurante.business.modelo.Patron_Comando.Orden;
import restaurante.business.modelo.Patron_Comando.OrdenCocinar;
import restaurante.business.modelo.Patron_Comando.OrdenRepartir;
import restaurante.business.modelo.Patron_Estado.EstadoCocinado;
import restaurante.business.modelo.Patron_Estado.EstadoPendiente;
import restaurante.domain.PedidoRestaurante;
import restaurante.persistance.PedidoRestauranteService;

import java.util.*;

public class MockEmisorOrdenes{
    private Queue<OrdenRepartir> ordenesARepartir;
    private Queue<OrdenCocinar> ordenesACocinar;
    private List<MockRepartidor> MockRepartidores;
    private List<MockCocinero> MockCocineros;

    private static MockEmisorOrdenes elEmisor;

    private MockEmisorOrdenes( boolean recuperarDatosDB) {
        ordenesARepartir = new ArrayDeque<OrdenRepartir>();
        ordenesACocinar = new ArrayDeque<OrdenCocinar>();
        MockCocineros = new ArrayList<MockCocinero>();
        MockRepartidores = new ArrayList<MockRepartidor>();
        if(recuperarDatosDB) recuperarOrdenesDB();
    }

    private void recuperarOrdenesDB() {
        PedidoRestauranteService pedidoRestauranteService = ServiceLocator.getPedidoRestauranteService();
        Iterator<PedidoRestaurante> iterator = pedidoRestauranteService.findAll().iterator();
        while (iterator.hasNext()){
            PedidoRestaurante p = iterator.next();
            if(p.getEstado() instanceof EstadoPendiente) ordenesACocinar.add(new OrdenCocinar(p));
            if(p.getEstado() instanceof EstadoCocinado) ordenesARepartir.add(new OrdenRepartir(p));
        }
    }

    public static MockEmisorOrdenes getEmisorOrdenes() {
        if (elEmisor == null) elEmisor = new MockEmisorOrdenes(true);
        return elEmisor;
    }

    public static MockEmisorOrdenes getEmisorOrdenes(boolean recuperarDatosDB) {
        if(recuperarDatosDB){
            return MockEmisorOrdenes.getEmisorOrdenes();
        }
        else{
            if (elEmisor == null) elEmisor = new MockEmisorOrdenes(false);
            return elEmisor;
        }
    }

    public void anyadirOrden(Orden orden) {
        if (orden instanceof OrdenRepartir) {
            ordenesARepartir.add((OrdenRepartir) orden);
        }
        if (orden instanceof OrdenCocinar) {
            ordenesACocinar.add((OrdenCocinar) orden);
        }
    }

    public void cancelarOrden(PedidoRestaurante elPedido) {
        for (OrdenCocinar orden : ordenesACocinar) {
            if (orden.getPedido().equals(elPedido)) {
                ordenesACocinar.remove(orden);
            }
        }
    }

    public Queue<OrdenRepartir> getOrdenesARepartir() {
        return ordenesARepartir;
    }

    public Queue<OrdenCocinar> getOrdenesACocinar() {
        return ordenesACocinar;
    }

    public void registrarMockRepartidor(MockRepartidor MockRepartidor) {
        MockRepartidores.add(MockRepartidor);
    }

    public void registrarMockCocinero(MockCocinero MockCocinero) {
        MockCocineros.add(MockCocinero);
    }

    public List<MockRepartidor> getMockRepartidores() {
        return MockRepartidores;
    }

    public List<MockCocinero> getMockCocineros() {
        return MockCocineros;
    }

    public void ejecutarThread(){
                    if(ordenesACocinar.size()>0) {
                        OrdenCocinar ordCocina = ordenesACocinar.element();
                        for (MockCocinero c : MockCocineros) {
                            if (c.isDisponible()) {
                                c.setDisponible(false);
                                MockCocineros.remove(c);
                                ordenesACocinar.remove(); //Eliminamos la 1ºorden
                                ordCocina.ejecutar(c);
                                break;
                            }
                        }
                    }
                    if(ordenesARepartir.size()>0) {
                        OrdenRepartir ordReparto = ordenesARepartir.element();
                        for (MockRepartidor r : MockRepartidores) {
                            if (r.isDisponible()) {
                                r.setDisponible(false);
                                MockRepartidores.remove(r);
                                ordenesARepartir.remove(); //Eliminamos la 1ºorden
                                ordReparto.ejecutar(r);
                                break;
                            }

                        }
                    }
                }

    public void vaciar(){
        ordenesARepartir = new ArrayDeque<OrdenRepartir>();
        ordenesACocinar = new ArrayDeque<OrdenCocinar>();
        MockCocineros = new ArrayList<MockCocinero>();
        MockRepartidores = new ArrayList<MockRepartidor>();
    }
}


    


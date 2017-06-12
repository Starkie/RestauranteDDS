package restaurante.business.modelo.Patron_Comando;

import persistance.ServiceLocator;
import restaurante.business.modelo.Patron_Estado.EstadoCocinado;
import restaurante.business.modelo.Patron_Estado.EstadoPendiente;
import restaurante.domain.PedidoRestaurante;
import restaurante.persistance.PedidoRestauranteService;

import java.util.*;

public class EmisorOrdenes {
    private Queue<OrdenRepartir> ordenesARepartir;
    private Queue<OrdenCocinar> ordenesACocinar;
    private List<Repartidor> repartidores;
    private List<Cocinero> cocineros;

    private static EmisorOrdenes elEmisor;

    private EmisorOrdenes( boolean recuperarDatosDB) {
        ordenesARepartir = new ArrayDeque<OrdenRepartir>();
        ordenesACocinar = new ArrayDeque<OrdenCocinar>();
        cocineros = new ArrayList<Cocinero>();
        repartidores = new ArrayList<Repartidor>();
        if(recuperarDatosDB) recuperarOrdenesDB();
        getThreadDisponibilidad().start();
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

    public static EmisorOrdenes getEmisorOrdenes() {
        if (elEmisor == null) elEmisor = new EmisorOrdenes(true);
        return elEmisor;
    }

    public static EmisorOrdenes getEmisorOrdenes(boolean recuperarDatosDB) {
        if(recuperarDatosDB){
            return getEmisorOrdenes();
        }
        else{
            if (elEmisor == null) elEmisor = new EmisorOrdenes(false);
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

    public void registrarRepartidor(Repartidor repartidor) {
        repartidores.add(repartidor);
    }

    public void registrarCocinero(Cocinero cocinero) {
        cocineros.add(cocinero);
    }

    public List<Repartidor> getRepartidores() {
        return repartidores;
    }

    public List<Cocinero> getCocineros() {
        return cocineros;
    }

    public Thread getThreadDisponibilidad() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(ordenesACocinar.size()>0) {
                        OrdenCocinar ordCocina = ordenesACocinar.element();
                        for (Cocinero c : cocineros) {
                            if (c.isDisponible()) {
                                c.setDisponible(false);
                                cocineros.remove(c);
                                ordenesACocinar.remove(); //Eliminamos la 1ºorden
                                ordCocina.ejecutar(c);
                                break;
                            }
                        }
                    }
                    if(ordenesARepartir.size()>0) {
                        OrdenRepartir ordReparto = ordenesARepartir.element();
                        for (Repartidor r : repartidores) {
                            if (r.isDisponible()) {
                                r.setDisponible(false);
                                repartidores.remove(r);
                                ordenesARepartir.remove(); //Eliminamos la 1ºorden
                                ordReparto.ejecutar(r);
                                break;
                            }

                        }
                    }
                }
            }
        });
    }

}

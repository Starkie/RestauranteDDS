package restaurante.modelo.Patron_Comando;

import model.Cocinero;
import model.PedidoRestaurante;
import model.Repartidor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class EmisorOrdenes {
    private Queue<OrdenRepartir> ordenesARepartir;
    private Queue<OrdenCocinar> ordenesACocinar;
    private List<Repartidor> repartidores;
    private List<Cocinero> cocineros;

    private static EmisorOrdenes elEmisor;

    private EmisorOrdenes() {
        ordenesARepartir = new ArrayDeque<OrdenRepartir>();
        ordenesACocinar = new ArrayDeque<OrdenCocinar>();
        cocineros = new ArrayList<Cocinero>();
        repartidores = new ArrayList<Repartidor>();
        getThreadDisponibilidad().start();
    }

    public static EmisorOrdenes getEmisorOrdenes() {
        if (elEmisor == null) elEmisor = new EmisorOrdenes();
        return elEmisor;
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
            if (orden.pedido.equals(elPedido)) {
                ordenesACocinar.remove(orden);
            }
        }
    }


    public void registrarRepartidor(Repartidor repartidor) {
        repartidores.add(repartidor);
    }

    public void registrarCocinero(Cocinero cocinero) {
        cocineros.add(cocinero);
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

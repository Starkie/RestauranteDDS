package model;

import restaurante.modelo.Patron_Comando.Repartidor;

public class Envio {
    private Repartidor repartidor;
    private PedidoRestaurante pedido;

    public Envio(Repartidor repartidor, PedidoRestaurante pedido) {
        this.repartidor = repartidor;
        this.pedido = pedido;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public PedidoRestaurante getPedido() {
        return pedido;
    }
}

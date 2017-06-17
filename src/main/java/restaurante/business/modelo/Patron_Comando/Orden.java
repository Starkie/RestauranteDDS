package restaurante.business.modelo.Patron_Comando;

import domain.Persona;
import restaurante.domain.PedidoRestaurante;

public abstract class Orden {
    protected PedidoRestaurante pedido;

    public Orden(PedidoRestaurante pedido) {
        this.pedido = pedido;
    }

    public abstract void ejecutar(Persona receptor);

    public PedidoRestaurante getPedido() {
        return pedido;
    }
}


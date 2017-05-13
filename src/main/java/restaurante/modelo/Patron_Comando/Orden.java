package restaurante.modelo.Patron_Comando;

import model.PedidoRestaurante;
import model.Persona;

public abstract class Orden {
    protected PedidoRestaurante pedido;

    public Orden(PedidoRestaurante pedido) {
        this.pedido = pedido;
    }

    public abstract void ejecutar(Persona receptor);
}

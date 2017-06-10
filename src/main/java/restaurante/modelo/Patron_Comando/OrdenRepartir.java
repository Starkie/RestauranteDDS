package restaurante.modelo.Patron_Comando;

import model.PedidoRestaurante;
import model.Persona;

public class OrdenRepartir extends Orden {

    public OrdenRepartir(PedidoRestaurante pedido) {
        super(pedido);
    }

    @Override
    public void ejecutar(Persona receptor) {
        ((Repartidor)receptor).realizarEnvio(pedido);
    }
}


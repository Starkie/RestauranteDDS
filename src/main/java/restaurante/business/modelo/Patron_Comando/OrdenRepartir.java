package restaurante.business.modelo.Patron_Comando;

import domain.Persona;
import restaurante.domain.PedidoRestaurante;

public class OrdenRepartir extends Orden {

    public OrdenRepartir(PedidoRestaurante pedido) {
        super(pedido);
    }

    @Override
    public void ejecutar(Persona receptor) {
        ((Repartidor)receptor).realizarEnvio(pedido);
    }
}


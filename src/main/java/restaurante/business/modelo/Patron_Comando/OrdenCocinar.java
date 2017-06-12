package restaurante.business.modelo.Patron_Comando;


import domain.Persona;
import restaurante.domain.PedidoRestaurante;

public class OrdenCocinar extends Orden {

    public OrdenCocinar(PedidoRestaurante pedido) {
        super(pedido);
    }

    @Override
    public void ejecutar(Persona receptor) {
        ((Cocinero)receptor).cocinarPedido(pedido);
    }
}

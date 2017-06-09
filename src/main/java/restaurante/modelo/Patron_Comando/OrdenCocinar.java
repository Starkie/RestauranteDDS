package restaurante.modelo.Patron_Comando;


import model.Cocinero;
import model.PedidoRestaurante;
import model.Persona;

public class OrdenCocinar extends Orden {

    public OrdenCocinar(PedidoRestaurante pedido) {
        super(pedido);
    }

    @Override
    public void ejecutar(Persona receptor) {
        ((Cocinero)receptor).cocinarPedido(pedido);
    }
}

package almacen.modelo.pedidos;

public class EstadoPedidoInvalidoException extends AlmacenException {
    public EstadoPedidoInvalidoException() {
        super();
    }

    public EstadoPedidoInvalidoException(String message) {
        super(message);
    }
}
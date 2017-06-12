package almacen.pedidos.domain;

public class EstadoPedidoInvalidoException extends AlmacenException {
    public EstadoPedidoInvalidoException() {
        super();
    }

    public EstadoPedidoInvalidoException(String message) {
        super(message);
    }
}

package almacen.pedidos.domain;

public abstract class AlmacenException extends Exception {

    public AlmacenException() {}

    public AlmacenException(String message) {
        super(message);
    }
}

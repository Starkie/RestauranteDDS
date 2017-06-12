package almacen.pedidos.model;

public enum EstadoPedido {
    PENDIENTE("Pendiente"), EN_CAMINO("En Camino"), COMPLETO("Completo"), CANCELADO("Cancelado");

    private String nombre;

    private EstadoPedido(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return nombre;
    }
}

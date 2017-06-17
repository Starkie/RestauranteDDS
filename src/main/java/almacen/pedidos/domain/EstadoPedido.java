package almacen.pedidos.domain;

public enum EstadoPedido {
    PENDIENTE("Pendiente"), EN_CAMINO("En Camino"), COMPLETO("Completo"), CANCELADO("Cancelado");

    private String nombre;

    EstadoPedido(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return nombre;
    }
}

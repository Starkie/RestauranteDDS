package almacen.pedidos.util;

import almacen.model.Producto;

public class FilaTabla {
    Producto producto;
    int unidades;

    public FilaTabla(Producto producto, int unidades) {
        this.producto = producto;
        this.unidades = unidades;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getUnidades() {
        return unidades;
    }
}

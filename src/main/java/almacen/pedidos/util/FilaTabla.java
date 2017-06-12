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

    public boolean equals(Object o) {
        return o instanceof FilaTabla
                &&  ((FilaTabla) o).getProducto().equals(producto)
                &&  ((FilaTabla) o).getUnidades() == unidades;
    }
}
